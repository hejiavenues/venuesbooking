CREATE DEFINER=`dev_risk`@`%` PROCEDURE `p_relationship`(IN v_mobile varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci)
BEGIN

		 select v_mobile name ,if(MAX(level ) is null,-1,MAX(level )) +2 category,70 symbolSize,1 label,100 value
			from risk_label  where mobile=v_mobile;

		 IF EXISTS (select * from  risk_relationship where mobile=v_mobile)THEN

		 drop table  if exists  tmp_relation1;	-- 一级关系
		 drop table  if exists  tmp_relation2;	-- 二级关系
		 drop table  if exists  tmp_relation3;	-- 用于一级关系中无标签但关联的二级关系有标签


		create   temporary TABLE   tmp_relation1(
			mobile VARCHAR(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
			level int(1),
			relationship VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
			rvalue VARCHAR(52) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
			fm VARCHAR(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci
		)  ;

	   INSERT into tmp_relation1 -- 查询一级关系   relationship 唯一关系 不能自己找自己 到此关系均为与被查人的关系
	 	 SELECT a.rmobile mobile ,
						MAX(b.`level`) level, -- 级别
						if(a.mobile>a.rmobile ,CONCAT(a.mobile,',',a.rmobile),CONCAT(a.rmobile,',',a.mobile) ) relationship,-- 唯一关系
						GROUP_CONCAT( DISTINCT a.source1) rvalue ,a.mobile fm -- 关系名称
		 from risk_relationship  a left join  risk_label b on a.rmobile=b.mobile
		 where a.mobile=v_mobile   and a.rmobile!=a.mobile
					 and a.rmobile  not in(select mobile from risk_relationship_filter ) GROUP BY a.rmobile

		 union  all

		 SELECT a.mobile ,
						MAX(b.`level`) level,
					if(a.mobile>a.rmobile ,CONCAT(a.mobile,',',a.rmobile),CONCAT(a.rmobile,',',a.mobile) ) relationship,
					GROUP_CONCAT(DISTINCT a.source1) rvalue ,a.rmobile fm
		 from risk_relationship  a left join  risk_label b on a.mobile=b.mobile
		 where a.rmobile=v_mobile   and rmobile!=a.mobile
						and a.mobile not in(select mobile from risk_relationship_filter ) GROUP BY a.mobile;


		 create   temporary TABLE   tmp_relation2
			(mobile VARCHAR(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
			level int(1),
			relationship VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
			rvalue VARCHAR(52) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
			fm VARCHAR(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci
		)  ;


		insert into   tmp_relation2  -- 二级关联 通过一级找 不能自己找自己 并且不能找已经找到的关系 即 不再找被查者


		SELECT a.rmobile   mobile,
					 MAX(b.`level`)  level,
			     if(a.mobile>a.rmobile ,CONCAT(a.mobile,',',a.rmobile),CONCAT(a.rmobile,',',a.mobile) ) relationship,
					 GROUP_CONCAT(DISTINCT a.source1) rvalue ,a.mobile fm
		 from risk_relationship  a left join  risk_label b on a.rmobile=b.mobile
		 where a.mobile in (select c.mobile from tmp_relation1 c  )
           and a.rmobile!=a.mobile and a.rmobile!=v_mobile  GROUP BY a.rmobile;

		INSERT into tmp_relation2
		SELECT a.mobile ,
					 MAX(b.`level`) `level`,
			    if(a.mobile>a.rmobile ,CONCAT(a.mobile,',',a.rmobile),CONCAT(a.rmobile,',',a.mobile) ) relationship,
					GROUP_CONCAT( DISTINCT a.source1) rvalue ,a.rmobile fm
		 from risk_relationship  a left join  risk_label b on a.mobile=b.mobile
		 where a.rmobile in  (select c.mobile from tmp_relation1 c )
           and a.rmobile!=a.mobile and a.mobile!=v_mobile GROUP BY a.mobile;

	create temporary TABLE   tmp_relation3
		        (mobile VARCHAR(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
			type int(1)

		)  ;



		INSERT into tmp_relation3
		select DISTINCT t1.mobile , 1 type from tmp_relation1 t1 left join tmp_relation2 t2 on t1.mobile=t2.fm
		where  t1.level is null and t2.level is not null ;

		INSERT into tmp_relation3
		select DISTINCT t1.mobile ,0 type from tmp_relation1 t1 left join tmp_relation2 t2 on t1.mobile=t2.fm
		where  t1.level is not  null ; -- 有标签

		INSERT into tmp_relation3
		select DISTINCT t1.mobile ,2 type from tmp_relation1 t1 left join tmp_relation2 t2 on t1.mobile=t2.fm
		where  t1.level is null and t2.level is  null  limit 50;  -- 保留100



    select 	mobile name ,
						IFNULL(max(level) ,-2) +2 category,
            if(max(level) is null ,20,max(level)*5+30) symbolSize,
            if(max(level) is null ,0,1) label,
            1 value from
    (select t1.mobile , if( t3.type= 1  ,-1, t1.level ) level
			from tmp_relation1 t1 left join  tmp_relation3 t3 on t1.mobile=t3.mobile
				where t3.mobile is not null
     union  all
     SELECT mobile,level from tmp_relation2 where level is not null   ) a
		GROUP BY a.mobile;

		select SUBSTRING_INDEX(relationship, ',', 1) source,REPLACE(relationship,CONCAT(SUBSTRING_INDEX(relationship, ',', 1),','),'') target ,GROUP_CONCAT( DISTINCT rvalue) value
		from
		(select    relationship ,GROUP_CONCAT( DISTINCT rvalue) rvalue
		from tmp_relation1  GROUP BY relationship
    union
		select    relationship,GROUP_CONCAT( DISTINCT rvalue) rvalue
		from tmp_relation2  where level is not null  GROUP BY relationship  ) a GROUP BY relationship;




	drop table  if exists  tmp_relation1;
	drop table  if exists  tmp_relation2;
	drop table  if exists  tmp_relation3;


	 end if;

END
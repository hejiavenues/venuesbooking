CREATE DEFINER=`dev_risk`@`%` PROCEDURE `p_report_cs_m1_period`(IN v_channel varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,IN v_orgId varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,IN v_beginTime varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,IN v_endTime varchar(50)  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,IN v_type varchar(50)  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,IN v_d1 varchar(50)  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,IN v_d2 varchar(50)  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci)
    SQL SECURITY INVOKER
BEGIN

			drop table  if exists  tmp_cs_day_org;	
			drop table  if exists  tmp_cs_day_org_sum;	
		  -- drop table  if exists  tmp_cs_day_org_all;		

			-- set v_beginTime=if(v_beginTime='',null,v_beginTime);
		

			if v_beginTime='' then 
			set v_beginTime = null ;
			end if;
			if v_endTime='' then 
			set v_endTime = null ;
			end if;
	

		
			if v_type='day' then 
			set v_type="%Y-%m-%d";
			end if ;
			if v_type='mon' then 
		   set	v_type="%Y-%m";
			end if ;
-- 建临时表

	  create   temporary TABLE   tmp_cs_day_org 
		(

	
     inner_time VARCHAR(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci , 
		  period	int(20),
		
		 inner_sum  int(20),
     out_sum int(20),
		 d0_sum int(20),
		 d1_sum int(20),
		 d2_sum int(20),
		 d0_rate decimal(10,2) ,
		 d1_rate decimal(10,2) , 
		 d2_rate decimal(10,2) ,
		 rate decimal(10,2) ,
		 inner_amount_sum int(20),
     out_amount_sum int(20),
		 d0_amount_sum int(20),
		 d1_amount_sum int(20),
		 d2_amount_sum int(20),
		 d0_amount_rate decimal(10,2) ,
		 d1_amount_rate decimal(10,2) , 
		 d2_amount_rate decimal(10,2) ,
		 amount_rate decimal(10,2) ,
		 count int(20)			
	  ) ;

			create temporary TABLE   tmp_cs_day_org_sum  like tmp_cs_day_org;

      


		insert into  tmp_cs_day_org
		 select	
						
							
							a.inner_time ,
							a.period,
							a.inner_sum,
							a.out_sum,
							a.d0_sum,
							a.d1_sum,
							a.d2_sum,
							ROUND(d0_sum/inner_sum*100,2) d0_rate,
							ROUND(d1_sum/inner_sum*100,2) d1_rate, 
							ROUND(d2_sum/inner_sum*100,2) d2_rate, 
							ROUND(out_sum/inner_sum*100,2) rate ,
						 	a.inner_amount_sum,
              a.out_amount_sum,
							a.d0_amount_sum,
							a.d1_amount_sum,
							a.d2_amount_sum,
							ROUND(d0_amount_sum/inner_amount_sum*100,2) d0_amount_rate,
							ROUND(d1_amount_sum/inner_amount_sum*100,2) d1_amount_rate, 
							ROUND(d2_amount_sum/inner_amount_sum*100,2) d2_amount_rate,
							ROUND(out_amount_sum/inner_amount_sum*100,2) amount_rate,
							1 count
             from (
	      
							select  DATE_FORMAT(a.inner_time,v_type ) inner_time,a.`level`,
											count(*) inner_sum, a.period,
											sum(if(out_org is null ,0,1)) out_sum,

											sum(if(DATEDIFF(out_time,inner_time)<1,1,0)) d0_sum,
											sum(if(DATEDIFF(out_time,inner_time)<v_d1,1,0)) d1_sum,
											sum(if(DATEDIFF(out_time,inner_time)<v_d2,1,0)) d2_sum,
											
											sum(inner_amount) inner_amount_sum,
											sum(out_amount) out_amount_sum,
										  sum(if(DATEDIFF(out_time,inner_time)<1,out_amount,0)) d0_amount_sum,
										  sum(if(DATEDIFF(out_time,inner_time)<v_d1,out_amount,0)) d1_amount_sum,
											sum(if(DATEDIFF(out_time,inner_time)<v_d2,out_amount,0)) d2_amount_sum

		          from r_case_log a left join sys_org b on a.inner_org =b.org_id 
		          where  a.`level` in ("S1","S2","S3","S4") and  a.channel=v_channel and 
							
							  if(v_beginTime  is not null ,
												convert(replace(DATE_FORMAT(a.inner_time,v_type ),'-',''),SIGNED ) >=convert(replace(v_beginTime,'-',''),SIGNED ) ,1=1)
								and 
							  if(v_endTime   is not null ,
						   	         convert(replace(DATE_FORMAT(a.inner_time,v_type ),'-',''),SIGNED ) <=  convert(replace(v_endTime,'-',''),SIGNED ),1=1)
		   GROUP BY   DATE_FORMAT(a.inner_time,v_type ),a.period ) a ;
			
			if (SELECT count(*) from tmp_cs_day_org)>0 then 


		insert into  tmp_cs_day_org_sum

			select  
					    
				
						  inner_time,	
							99 period,
							sum(inner_sum) inner_sum,
							sum(out_sum) out_sum,
							sum(a.d0_sum) d0_sum,
							sum(a.d1_sum) d1_sum,
							sum(a.d2_sum) d2_sum,
							ROUND(d0_sum/inner_sum*100,2) d0_rate,
							ROUND(d1_sum/inner_sum*100,2) d1_rate, 
							ROUND(d2_sum/inner_sum*100,2) d2_rate, 
							ROUND(sum(out_sum) /sum(inner_sum)*100,2) rate ,
						 	sum(a.inner_amount_sum) inner_amount_sum,
              sum(a.out_amount_sum) out_amount_sum,
							sum(a.d0_amount_sum) d0_amount_sum,
							sum(a.d1_amount_sum) d1_amount_sum,
							sum(a.d2_amount_sum) d2_amount_sum,
							ROUND(sum(d0_amount_sum)/sum(inner_amount_sum)*100,2) d0_amount_rate,
							ROUND(sum(d1_amount_sum)/sum(inner_amount_sum)*100,2) d1_amount_rate, 
							ROUND(sum(d2_amount_sum)/sum(inner_amount_sum)*100,2) d2_amount_rate,
							ROUND(sum(out_amount_sum)/sum(inner_amount_sum)*100,2) amount_rate,
							sum(count)+1 count
				
			from tmp_cs_day_org a GROUP BY  inner_time;

			insert into tmp_cs_day_org 
			select  						
							now() inner_time ,
							9999 period,
							sum(inner_sum) inner_sum,
							sum(out_sum) out_sum,
							sum(a.d0_sum) d0_sum,
							sum(a.d1_sum) d1_sum,
							sum(a.d2_sum) d2_sum,
							ROUND(d0_sum/inner_sum*100,2) d0_rate,
							ROUND(d1_sum/inner_sum*100,2) d1_rate, 
							ROUND(d2_sum/inner_sum*100,2) d2_rate, 
							ROUND(sum(out_sum) /sum(inner_sum)*100,2) rate ,
						 	sum(a.inner_amount_sum) inner_amount_sum,
              sum(a.out_amount_sum) out_amount_sum,
							sum(a.d0_amount_sum) d0_amount_sum,
							sum(a.d1_amount_sum) d1_amount_sum,
							sum(a.d2_amount_sum) d2_amount_sum,
							ROUND(sum(d0_amount_sum)/sum(inner_amount_sum)*100,2) d0_amount_rate,
							ROUND(sum(d1_amount_sum)/sum(inner_amount_sum)*100,2) d1_amount_rate, 
							ROUND(sum(d2_amount_sum)/sum(inner_amount_sum)*100,2) d2_amount_rate,
							ROUND(sum(out_amount_sum)/sum(inner_amount_sum)*100,2) amount_rate,
							0 count
			from tmp_cs_day_org_sum a;

		  -- create temporary TABLE   tmp_cs_day_org_all as 
			select * from (
				select * from tmp_cs_day_org
				union ALL
				select * from tmp_cs_day_org_sum) a
			 ORDER BY  a.inner_time,period  ;
		
	
		  -- select  * from tmp_cs_day_org_all;
		  -- select  inner_time span ,count(*) count from tmp_cs_day_org_all GROUP BY inner_time;
		  -- select  CONCAT(inner_time,',',inner_org) span ,count(*) count from tmp_cs_day_org_all GROUP BY inner_time,inner_org;

		end if;
			
			drop table  if exists  tmp_cs_day_org;	
			drop table  if exists  tmp_cs_day_org_sum;		
			-- drop table  if exists  tmp_cs_day_org_all;		
 
END
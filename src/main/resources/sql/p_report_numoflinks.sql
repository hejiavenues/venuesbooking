CREATE DEFINER=`dev_risk`@`%` PROCEDURE `p_report_numoflinks`(IN v_channel varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,IN v_beginTime varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,IN v_endTime varchar(50)  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,IN v_subChannel varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci, IN v_productId varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci)
BEGIN


		-- DECLARE v_table VARCHAR(200);
		-- DECLARE v_sql_create_table VARCHAR(2000);
		-- DECLARE v_sql_drop_table VARCHAR(2000);
		-- DECLARE v_sql_insert_table VARCHAR(2000);
		-- DECLARE v_sql_insert_sum VARCHAR(2000);
		-- DECLARE v_sql_insert_select VARCHAR(2000);
		-- SET @vsql0=v_sql_create_table;
	  -- PREPARE vsql0 FROM @vsql0;
	  -- EXECUTE vsql0;
	  -- DEALLOCATE PREPARE vsql0 ;   -- 建表完成

		 drop table  if exists  tmp_report;
		 drop table  if exists  tmp_report_sum;

	   create   temporary TABLE   tmp_report
		(channel VARCHAR(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
		sub_channel VARCHAR(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
		create_time VARCHAR(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
		sub_channel_name VARCHAR(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
		click_sum int(20),
		mobile_sum int(20),
		credit_sum int(20),
		credit_success int(20),
		decision_sum int(20),
		risk_success int(20),
		dt_sum int(20),
		wait_dt int(20),
		dt_success int(20),
		dt_fial int(20),
		wait_dt2 int(20),
		dt2_success int(20),
		dt2_fail int(20),
		trade_success int(20),
		trade_fail int(20)
	)  ;

  create   temporary TABLE   tmp_report_sum
		(channel VARCHAR(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
		sub_channel VARCHAR(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
		create_time VARCHAR(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
		sub_channel_name VARCHAR(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
		click_sum int(20),
		mobile_sum int(20),
		credit_sum int(20),
		credit_success int(20),
		decision_sum int(20),
		risk_success int(20),
		dt_sum int(20),
		wait_dt int(20),
		dt_success int(20),
		dt_fial int(20),
		wait_dt2 int(20),
		dt2_success int(20),
		dt2_fail int(20),
		trade_success int(20),
		trade_fail int(20)
	)  ;





  insert into tmp_report
	select v_channel   ,ch.sub_channel,ch.dt  , ch.sub_channel_name , a.click_sum ,a.mobile_sum,
	s.credit_sum,s.credit_success,s.decision_sum,s.risk_success,
	d.dt_sum  ,  d.wait_dt ,td.dt_success, td.dt_fial ,d.wait_dt2,td2.dt2_success,td2.dt2_fail ,
	f.trade_success,f.trade_fail

	 from
	(
		SELECT DATE(create_time) dt,sub_channel,sub_channel_name
		FROM report_everyday
		where  channel=v_channel
				and if(v_beginTime is not null and v_beginTime !='' and v_endTime is not null and v_endTime !='',
						DATE(create_time) BETWEEN v_beginTime and v_endTime ,1=1)
				and if(v_subChannel is not null and v_subChannel  !='',
     sub_channel=v_subChannel ,1=1)
				and if(v_productId  is not null and v_productId   !='',product_id=v_productId  ,1=1)
			GROUP BY  DATE(create_time),sub_channel
				union
			select DATE(order_time) ,sub_channel ,sub_channel_name
			from report_everyday where  channel=v_channel
				and if(v_beginTime is not null and v_beginTime !='' and v_endTime is not null and v_endTime !='',
						DATE(order_time) BETWEEN v_beginTime and v_endTime ,1=1)
				and if(v_subChannel is not null and v_subChannel  !='',sub_channel=v_subChannel ,1=1)
				and if(v_productId  is not null and v_productId   !='',product_id=v_productId  ,1=1)
				group by DATE(order_time), sub_channel
					union
				select date(gmt_create),sub_channel ,sub_channel_name
				from  report_everyday where  channel=v_channel
				and if(v_beginTime is not null and v_beginTime !='' and v_endTime is not null and v_endTime !='',
						DATE(gmt_create) BETWEEN v_beginTime and v_endTime ,1=1)
				and if(v_subChannel is not null and v_subChannel  !='',sub_channel=v_subChannel ,1=1)
				and if(v_productId  is not null and v_productId   !='',product_id=v_productId  ,1=1)
				group by date(gmt_create),sub_channel
		union
		select date(loan_create_time) ,sub_channel,sub_channel_name
		from report_everyday DT where  channel=v_channel
				and if(v_beginTime is not null and v_beginTime !='' and v_endTime is not null and v_endTime !='',
						DATE(loan_create_time) BETWEEN v_beginTime and v_endTime ,1=1)
				and if(v_subChannel is not null and v_subChannel  !='',sub_channel=v_subChannel ,1=1)
				and if(v_productId  is not null and v_productId   !='',product_id=v_productId  ,1=1)
		group by date(loan_create_time),sub_channel
				union
		select date(td_time) ,sub_channel,sub_channel_name
		from report_everyday DT where  channel=v_channel
				and if(v_beginTime is not null and v_beginTime !='' and v_endTime is not null and v_endTime !='',
						DATE(td_time) BETWEEN v_beginTime and v_endTime ,1=1)
				and if(v_subChannel is not null and v_subChannel  !='',sub_channel=v_subChannel ,1=1)
				and if(v_productId  is not null and v_productId   !='',product_id=v_productId  ,1=1)
		group by date(td_time),sub_channel
	union
		select date(td2_time) ,sub_channel,sub_channel_name
		from report_everyday DT where  channel=v_channel
				and if(v_beginTime is not null and v_beginTime !='' and v_endTime is not null and v_endTime !='',
						DATE(td2_time) BETWEEN v_beginTime and v_endTime ,1=1)
				and if(v_subChannel is not null and v_subChannel  !='',sub_channel=v_subChannel ,1=1)
				and if(v_productId  is not null and v_productId   !='',product_id=v_productId  ,1=1)
		group by date(td2_time),sub_channel

			) ch  -- 查询所有渠道

	left join

	(SELECT DATE(t.create_time) dt,t.sub_channel ,t.sub_channel_name ,
	max(t.click_sum) click_sum ,count(DISTINCT t.encrypt_mobile)   mobile_sum  FROM report_everyday t
	where  channel=v_channel
	and if(v_beginTime is not null and v_beginTime !='' and v_endTime is not null and v_endTime !='',
						DATE(create_time) BETWEEN v_beginTime and v_endTime ,1=1)
				and if(v_subChannel is not null and v_subChannel  !='',sub_channel=v_subChannel ,1=1)
				and if(v_productId  is not null and v_productId   !='',product_id=v_productId  ,1=1)
	GROUP BY  DATE(t.create_time),t.sub_channel) a  on ch.sub_channel=a.sub_channel and ch.dt=a.dt  -- 注册当日相关数据

	left join

	(select DATE(order_time) dt,sub_channel ,
	SUM(if(apply_type='Credit',1,0 )) credit_sum,
	sum(if(apply_type='Credit' and audit_result='0' ,1,0)) credit_success,
	SUM(if(apply_type='Decision' , 1 , 0 ) ) decision_sum,
	SUM(if(apply_type='Decision' AND audit_result='0' , 1 , 0 ) ) risk_success
	from report_everyday
	where channel=v_channel
				and if(v_beginTime is not null and v_beginTime !='' and v_endTime is not null and v_endTime !='',
						DATE(order_time) BETWEEN v_beginTime and v_endTime ,1=1)
				and if(v_subChannel is not null and v_subChannel  !='',sub_channel=v_subChannel ,1=1)
				and if(v_productId  is not null and v_productId   !='',product_id=v_productId  ,1=1)
	group by DATE(order_time), sub_channel  )s  ON s.dt=ch.dt and s.sub_channel=ch.sub_channel     --  统计日-授信进件情况

		left join

		(select date(gmt_create) date ,sub_channel ,
		SUM(if(DT.gmt_create is not null , 1 , 0 ) ) AS dt_sum,
		SUM(if(DT.decision_result='9' , 1 , 0 ) ) AS wait_dt,
		SUM(if(DT.decision_result='10' , 1 , 0 ) ) AS wait_dt2
		from  report_everyday dt
		where channel=v_channel
		and if(v_beginTime is not null and v_beginTime !='' and v_endTime is not null and v_endTime !='',
						DATE(gmt_create) BETWEEN v_beginTime and v_endTime ,1=1)
				and if(v_subChannel is not null and v_subChannel  !='',sub_channel=v_subChannel ,1=1)
				and if(v_productId  is not null and v_productId   !='',product_id=v_productId  ,1=1)
		group by date(gmt_create),sub_channel
		)d ON d.date=ch.dt and d.sub_channel=ch.sub_channel  -- 统 计日-进入电核情况

		left join
		(select  date(td_time) date ,sub_channel ,
		SUM(if(DT.td_result ='10' , 1 , 0 ) ) AS dt_success,
		SUM(if(DT.td_result='1' , 1 , 0 ) ) AS dt_fial
		from  report_everyday dt
		where channel=v_channel
		and if(v_beginTime is not null and v_beginTime !='' and v_endTime is not null and v_endTime !='',
						DATE(td_time) BETWEEN v_beginTime and v_endTime ,1=1)
				and if(v_subChannel is not null and v_subChannel  !='',sub_channel=v_subChannel ,1=1)
				and if(v_productId  is not null and v_productId   !='',product_id=v_productId  ,1=1)
		group by date(td_time),sub_channel
		)td ON td.date=ch.dt and td.sub_channel=ch.sub_channel  --  一级电核情况

		left join

			(select date(td2_time) date ,sub_channel ,

		SUM(if(DT.td2_result='0' , 1 , 0 ) ) AS dt2_success,
		SUM(if(DT.td2_result='1' , 1 , 0 ) ) AS dt2_fail
		from  report_everyday dt
		where channel=v_channel
		and if(v_beginTime is not null and v_beginTime !='' and v_endTime is not null and v_endTime !='',
						DATE(td2_time) BETWEEN v_beginTime and v_endTime ,1=1)
				and if(v_subChannel is not null and v_subChannel  !='',sub_channel=v_subChannel ,1=1)
				and if(v_productId  is not null and v_productId   !='',product_id=v_productId  ,1=1)
		group by date(td2_time),sub_channel
		)td2 ON td2.date=ch.dt and td2.sub_channel=ch.sub_channel  -- 二级电核情况


		left join
		(select date(loan_create_time) date,sub_channel,  -- 统计放款情况
		SUM(if( DT.trade_satus='30', 1 , 0 ) ) AS  trade_success,
		SUM(if(DT.trade_satus='31' , 1 , 0 ) ) AS  trade_fail
		from report_everyday DT
		where channel=v_channel
		and if(v_beginTime is not null and v_beginTime !='' and v_endTime is not null and v_endTime !='',
						DATE(loan_create_time) BETWEEN v_beginTime and v_endTime ,1=1)
				and if(v_subChannel is not null and v_subChannel  !='',sub_channel=v_subChannel ,1=1)
				and if(v_productId  is not null and v_productId   !='',product_id=v_productId  ,1=1)
		group by date(loan_create_time),sub_channel)f ON f.date=ch.dt and f.sub_channel=ch.sub_channel;

			-- select channel , sub_channel , 	create_time , sub_channel_name , click_sum , mobile_sum ,
	  -- credit_sum , credit_success ,decision_sum ,risk_success ,dt_sum ,wait_dt ,dt_success ,dt_fial ,wait_dt2 ,
	 --  dt2_success ,dt2_fail ,trade_success ,trade_fail


				-- create   temporary TABLE tmp_report_sum as
	insert into tmp_report_sum
				select max(channel) , 999999999 , 	create_time , '合计' , sum(click_sum) ,  sum(mobile_sum),
	       sum(credit_sum ),  sum(credit_success) , sum(decision_sum ), sum(risk_success ), sum(dt_sum ), sum(wait_dt) , sum(dt_success ), sum(dt_fial) ,
			   sum(wait_dt2 ), sum(dt2_success) , sum(dt2_fail) , sum(trade_success), sum(trade_fail)
				from tmp_report  GROUP BY create_time;

			select * from (
			 select * from tmp_report
				union ALL
			select * from tmp_report_sum) a  order by a.create_time,a.sub_channel;
     -- order by create_time,sub_channel;

	   drop table  if exists  tmp_report;
     drop table  if exists  tmp_report_sum;
END
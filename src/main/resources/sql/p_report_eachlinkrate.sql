CREATE DEFINER=`dev_risk`@`%` PROCEDURE `p_report_eachlinkrate`(IN v_channel varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,IN v_beginTime varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,IN v_endTime varchar(50)  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,IN v_subChannel varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci, IN v_productId varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci, IN v_onlySameDay varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci)
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

		 drop table  if exists  tmp_report1;
		 drop table  if exists  tmp_report1_sum;

	   create   temporary TABLE   tmp_report1
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

	create   temporary TABLE   tmp_report1_sum
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


  insert into tmp_report1 ( channel , sub_channel , 	create_time , sub_channel_name , click_sum , mobile_sum ,
	   credit_sum , credit_success ,decision_sum ,risk_success ,dt_sum ,wait_dt ,dt_success ,dt_fial ,wait_dt2 ,
	   dt2_success ,dt2_fail ,trade_success ,trade_fail )
	SELECT v_channel,DT.sub_channel ,DATE(DT.create_time) create_time,DT.sub_channel_name ,
	max(DT.click_sum) click_sum ,count(DISTINCT DT.encrypt_mobile)   mobile_sum ,
	SUM(if(if(v_onlySameDay='true', date(order_time)=date(create_time),1=1) and apply_type='Credit',1,0 )) credit_sum,
	sum(if(if(v_onlySameDay='true', date(order_time)=date(create_time),1=1) and apply_type='Credit' and audit_result='0' ,1,0)) credit_success,
	SUM(if(if(v_onlySameDay='true', date(order_time)=date(create_time),1=1) and apply_type='Decision' , 1 , 0 ) ) decision_sum,
	SUM(if(if(v_onlySameDay='true', date(order_time)=date(create_time),1=1) and apply_type='Decision' AND audit_result='0' , 1 , 0 ) ) risk_success,
	SUM(if(if(v_onlySameDay='true', date(order_time)=date(create_time),1=1) and DT.`apply_type`='Decision' and DT.`audit_result`='0'  and DT.gmt_create is not null , 1 , 0 ) ) AS dt_sum,
	SUM(if(if(v_onlySameDay='true', date(order_time)=date(create_time),1=1) and DT.`apply_type`='Decision' and DT.`audit_result`='0'  and DT.`decision_result`='9' , 1 , 0 ) ) AS wait_dt,
	SUM(if(if(v_onlySameDay='true', date(order_time)=date(create_time),1=1) and DT.`apply_type`='Decision' and DT.`audit_result`='0'  and DT.`td_result` ='10' , 1 , 0 ) ) AS dt_success,
	SUM(if(if(v_onlySameDay='true', date(order_time)=date(create_time),1=1) and DT.`apply_type`='Decision' and DT.`audit_result`='0'  and DT.`td_result`='1' , 1 , 0 ) ) AS dt_fial,
	SUM(if(if(v_onlySameDay='true', date(order_time)=date(create_time),1=1) and DT.`apply_type`='Decision' and DT.`audit_result`='0'  and DT.`decision_result`='10' , 1 , 0 ) ) AS wait_dt2,
	SUM(if(if(v_onlySameDay='true', date(order_time)=date(create_time),1=1) and DT.`apply_type`='Decision' and DT.`audit_result`='0'  and DT.`td2_result`='0' , 1 , 0 ) ) AS dt2_success,
	SUM(if(if(v_onlySameDay='true', date(order_time)=date(create_time),1=1) and DT.`apply_type`='Decision' and DT.`audit_result`='0'  and DT.`td2_result`='1' , 1 , 0 ) ) AS dt2_fail ,
	SUM(if(if(v_onlySameDay='true', date(order_time)=date(create_time),1=1) and  DT.`apply_type`='Decision' and DT.`audit_result`='0' and DT.trade_satus='30', 1 , 0 ) ) AS  trade_success,
	SUM(if(if(v_onlySameDay='true', date(order_time)=date(create_time),1=1) and  DT.`apply_type`='Decision' and DT.`audit_result`='0' and DT.`trade_satus`='31' , 1 , 0 ) ) AS  trade_fail
	FROM report_everyday DT
	where  channel=v_channel
				and if(v_beginTime is not null and v_beginTime !='' and v_endTime is not null and v_endTime !='',
						DATE(create_time) BETWEEN v_beginTime and v_endTime ,1=1)
				and if(v_subChannel is not null and v_subChannel  !='', sub_channel=v_subChannel ,1=1)
				and if(v_productId  is not null and v_productId   !='',product_id=v_productId  ,1=1)
	GROUP BY  DATE(create_time),sub_channel;

	-- create   temporary TABLE   tmp_report1_sum as
	insert into tmp_report1_sum
	select max(channel) channel, 999999999 sub_channel, 	create_time create_time , '合计' sub_channel_name , sum(click_sum) click_sum ,  sum(mobile_sum ) mobile_sum,
	 sum(credit_sum ) credit_sum,  sum(credit_success) credit_success , sum(decision_sum ) decision_sum, sum(risk_success )risk_success, sum(dt_sum ) dt_sum, sum(wait_dt) wait_dt, sum(dt_success )dt_success,
   sum(dt_fial)dt_fial , sum(wait_dt2 ) wait_dt2, sum(dt2_success)dt2_success , sum(dt2_fail) dt2_fail, sum(trade_success)trade_success, sum(trade_fail)trade_fail
	from tmp_report1  GROUP BY create_time;

			select
			concat(truncate(if(a.mobile_sum>a.click_sum ,1,a.mobile_sum/a.click_sum) *100,2),'%') click_to_mobile,
			concat(truncate(if(a.risk_success>a.mobile_sum ,1,a.risk_success/a.mobile_sum) *100,2),'%') mobile_to_risks ,
			concat(truncate(if(a.dt2_success>a.mobile_sum ,1,a.dt2_success/a.mobile_sum) *100,2),'%') mobile_to_td2s,
			concat(truncate(if(a.trade_success>a.mobile_sum ,1,a.trade_success/a.mobile_sum) *100,2),'%') mobile_to_trades ,

			concat(truncate(if(a.credit_sum>a.mobile_sum ,1,a.credit_sum/a.mobile_sum) *100,2),'%') mobile_to_credit,
			concat(truncate(if(a.credit_success>a.mobile_sum ,1,a.credit_success/a.mobile_sum) *100,2),'%') mobile_to_credits,

			concat(truncate(if(a.risk_success>a.mobile_sum ,1,a.risk_success/a.credit_sum) *100,2),'%') credit_to_risks,

			if(dt_success>0,concat(truncate(if(a.dt_success>(a.dt_success+a.dt_fial) ,1,a.dt_success/(a.dt_success+a.dt_fial)) *100,2),'%'),'0.00%')dt_to_dts,
			if(dt2_success>0,concat(truncate(if(a.dt2_success>(a.dt2_success+a.dt2_fail) ,1,a.dt2_success/(a.dt2_success+a.dt2_fail)) *100,2),'%'),'0.00%')dt2_to_dt2s,
			concat(truncate(if(a.trade_success>a.dt2_success ,1,a.trade_success/a.dt2_success) *100,2),'%') dt2s_to_trades,
			a.* from (
			 select * from tmp_report1
				union ALL
			select * from tmp_report1_sum) a ORDER BY a.create_time,a.sub_channel ;
     -- order by create_time,sub_channel;

	   drop table  if exists  tmp_report1;
     drop table  if exists  tmp_report_sum1;
END
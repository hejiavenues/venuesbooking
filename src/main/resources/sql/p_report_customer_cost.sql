CREATE DEFINER=`dev_risk`@`%` PROCEDURE `p_report_customer_cost`(IN v_channel varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,IN v_beginTime varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,IN v_endTime varchar(50)  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,IN v_subChannel varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci, IN v_productId varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci)
BEGIN


		 drop table  if exists  tmp_report_cost;
		 drop table  if exists  tmp_report_cost_sum;


		create   temporary TABLE   tmp_report_cost as
		SELECT v_channel channel, sub_channel ,sub_channel_name, date(CREATE_time) create_time ,count(DISTINCT encrypt_mobile ) mobile_sum,
						SUM(if(apply_type='Credit',1,0 )) credit_sum,
						SUM(if(apply_type='Decision' and `audit_result`='0' and trade_satus='30', 1 , 0 ) ) AS  trade_success
		from report_everyday
		where channel=v_channel
				and if(v_beginTime is not null and v_beginTime !='' and v_endTime is not null and v_endTime !='',
						DATE(create_time) BETWEEN v_beginTime and v_endTime ,1=1)
				and if(v_subChannel is not null and v_subChannel  !='',sub_channel=v_subChannel ,1=1)
				and if(v_productId  is not null and v_productId   !='',product_id=v_productId  ,1=1)
    GROUP BY sub_channel,date(create_time) ;

		create  temporary TABLE  tmp_report_cost_sum as
		SELECT  channel, sub_channel ,sub_channel_name,  '合计' create_time ,sum(mobile_sum) mobile_sum,
						SUM(credit_sum) credit_sum,
						SUM(trade_success) AS trade_success
		from tmp_report_cost GROUP BY sub_channel;




		select a.*,concat(truncate((a.trade_success/mobile_sum) *100,2),'%') rate,
		if(b.cost is null ,'成本未设定',b.cost) channel_cost,
		if(trade_success=0,'未获客','有获客') lending_situation,
    a.mobile_sum*IFNULL(b.cost,0)/if(a.trade_success=0 or a.trade_success is null  ,1,a.trade_success) price

		from (
			select 1 type,m.* from
			tmp_report_cost_sum m
			union ALL
			select 2 type,n.* from
			tmp_report_cost n
		 ) a  left join saas_api_master.channel_subchannel b on a.sub_channel=b.sub_channel_code

		order by type ,price desc , a.sub_channel, a.create_time ;


	   drop table  if exists  tmp_report_cost;
     drop table  if exists  tmp_report_cost_sum;


END
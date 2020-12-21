CREATE ALGORITHM = UNDEFINED DEFINER = `dev_risk`@`%` SQL SECURITY DEFINER VIEW `r_case_log` AS SELECT
        `a`.`channel` AS `channel` ,
        `a`.`apply_code` AS `apply_code` ,
        `a`.`level` AS `level` ,
        `a`.`period` AS `period` ,
        `a`.`b_id` AS `b_id` ,
        `a`.`inner_amount` AS `inner_amount` ,
        `a`.`inner_org` AS `inner_org` ,
        `a`.`inner_collector` AS `inner_collector` ,
        `a`.`inner_time` AS `inner_time` ,
        `a`.`inner_type` AS `inner_type` ,
        `a`.`c_id` AS `c_id` ,
        `a`.`out_org` AS `out_org` ,
        `a`.`out_collector` AS `out_collector` ,
        `a`.`out_time` AS `out_time` ,
        `a`.`out_amount` AS `out_amount` ,
        `a`.`out_desc` AS `out_desc`
      FROM
        (
          SELECT
            `a`.`channel` AS `channel` ,
            `a`.`apply_code` AS `apply_code` ,
            `a`.`level` AS `level` ,
            `a`.`period` AS `period` ,
            `b`.`b_id` AS `b_id` ,
            `b`.`inner_amount` AS `inner_amount` ,
            `b`.`inner_org` AS `inner_org` ,
            `b`.`inner_collector` AS `inner_collector` ,
            `b`.`inner_time` AS `inner_time` ,
            `b`.`inner_type` AS `inner_type` ,
            `c`.`c_id` AS `c_id` ,
            `c`.`out_org` AS `out_org` ,
            `c`.`out_collector` AS `out_collector` ,
            `c`.`out_time` AS `out_time` ,
            `c`.`out_amount` AS `out_amount` ,
            `c`.`out_desc` AS `out_desc`
          FROM
            (
                (
                    (
                      (
                        SELECT
                           `c_case_log`.`channel` AS `channel` ,
                           `c_case_log`.`apply_code` AS `apply_code` ,
                           `c_case_log`.`level` AS `level` ,
                           `c_case_log`.`period` AS `period`
                        FROM
                           `c_case_log`
                        GROUP BY
                           `c_case_log`.`apply_code` ,
                           `c_case_log`.`level` ,
                           `c_case_log`.`period`
                      )
                    ) `a`
                    LEFT JOIN(
                               SELECT
                                  `c_case_log`.`apply_code` AS `apply_code` ,
                                  `c_case_log`.`level` AS `level` ,
                                  `c_case_log`.`period` AS `period` ,
                                  `c_case_log`.`id` AS `b_id` ,
                                  `c_case_log`.`type` AS `inner_type` ,
                                  `c_case_log`.`org_id` AS `inner_org` ,
                                  `c_case_log`.`collector_id` AS `inner_collector` ,
                                  `c_case_log`.`create_time` AS `inner_time` ,
                                  `c_case_log`.`amount` AS `inner_amount`
                               FROM
                                  `c_case_log`
                               WHERE
                                 (
                                    `c_case_log`.`type` IN('1' , '4')
                                 )
                             ) `b` ON(
                    (
                      (
                        `a`.`apply_code` = `b`.`apply_code`
                      )
                      AND(`a`.`level` = `b`.`level`)
                      AND(`a`.`period` = `b`.`period`)
                    )
                    )
                  )
                LEFT JOIN(
                           SELECT
                              `c_case_log`.`apply_code` AS `apply_code` ,
                              `c_case_log`.`level` AS `level` ,
                              `c_case_log`.`period` AS `period` ,
                              `c_case_log`.`id` AS `c_id` ,
                              `c_case_log`.`org_id` AS `out_org` ,
                              `c_case_log`.`collector_id` AS `out_collector` ,
                              `c_case_log`.`create_time` AS `out_time` ,
                              `c_case_log`.`amount` AS `out_amount` ,
                              `c_case_log`.`log_desc` AS `out_desc`
                           FROM
                              `c_case_log`
                           WHERE
                             (
                                `c_case_log`.`type` = 2
                             )
                         ) `c` ON(
                (
                  (
                    `a`.`apply_code` = `c`.`apply_code`
                  )
                  AND(`a`.`level` = `c`.`level`)
                  AND(`a`.`period` = `c`.`period`)
                )
                )
            )
          ORDER BY
            `b`.`b_id` DESC ,
            `c`.`c_id` DESC
          LIMIT 999999999
        ) `a`
      GROUP BY
        `a`.`apply_code` ,
        `a`.`level` ,
        `a`.`period`
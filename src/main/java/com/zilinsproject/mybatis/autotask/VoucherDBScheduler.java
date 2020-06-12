package com.zilinsproject.mybatis.autotask;

import com.zilinsproject.mybatis.dao.SchedulerMapper;
import com.zilinsproject.mybatis.dao.VoucherArchivedMapper;
import com.zilinsproject.mybatis.dao.VoucherMapperExtended;
import com.zilinsproject.mybatis.entity.Scheduler;
import com.zilinsproject.mybatis.entity.Voucher;
import com.zilinsproject.mybatis.entity.VoucherArchived;
import com.zilinsproject.mybatis.utils.SchedulerConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.util.Date;
import java.util.List;

/**
 * 优惠券日期检查定时器
 * @author zilinsmac
 */

@Slf4j
@Configuration
@EnableScheduling
public class VoucherDBScheduler implements SchedulingConfigurer {

    @Autowired
    private SchedulerMapper schedulerMapper;
    @Autowired
    private VoucherMapperExtended voucherMapper;
    @Autowired
    private VoucherArchivedMapper voucherArchivedMapper;


    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {

        scheduledTaskRegistrar.addTriggerTask(() -> dateScheduler(),
                triggerContext -> {
                    Scheduler scheduler = schedulerMapper.selectByPrimaryKey(SchedulerConst.VOUCHER_DATE_VALIDATION_SCHEDULER_ID);
                    if (scheduler == null){
                        log.info("cron为空");
                    }
                    String cron = scheduler.getCron();
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }

        );

    }

    private void dateScheduler(){
        //查询所有优惠券是否过期
        Date now = new Date();
        List<Voucher> voucherList = voucherMapper.selectAll();
        voucherList.forEach(voucher -> {
            if (voucher.getEnd_date().compareTo(now) <= 0){
                //过期或无效逻辑删除
                voucherMapper.deleteByPrimaryKey(voucher.getVoucher_id());
                VoucherArchived voucherArchived = new VoucherArchived();
                BeanUtils.copyProperties(voucher, voucherArchived);
                voucherArchivedMapper.insert(voucherArchived);
            }
        });
        log.info("定时器检查优惠券过期时间");

    }
}

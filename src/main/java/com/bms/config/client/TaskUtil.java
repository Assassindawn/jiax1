package com.bms.config.client;

import com.bms.pojo.enums.QosEnum;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class TaskUtil {
    @Resource
    private EmqClient emqClient;
    private static final Logger log= LoggerFactory.getLogger(MqttCallback.class);
    private static String  nowdate="yyyyMMddHHmmss";
    // 添加定时任务

    // 0/25 从0秒开始 每隔25秒 * 所有值   ? 不指定值
    @Scheduled(cron = "25 * * * * ? ") // cron表达式
    public void doTask(){
//        try {
//            TimeUnit.SECONDS.sleep(2000);beat
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(nowdate);
        String Str = dateFormat.format(now);
        emqClient.publish("Heartbeat", "live1:", QosEnum.Qos0, false);

    }

//0秒 0分 18时 ?不指定日期 *所有可能的月 ?不指定星期 0 0 18 ? * ?
    @Scheduled(cron = "0 0 18 * * ? ")
    public void doTask2(){

        emqClient.publish("/iot/1206/wsy", "{\"target\":\"beep\",\"value\":1}", QosEnum.Qos0, false);
        log.info("光照灯开");
    }
    @Scheduled(cron = "0 0 6 * * ? ")
    public void doTask3(){

        emqClient.publish("/iot/1206/wsy", "{\"target\":\"beep\",\"value\":0}", QosEnum.Qos0, false);
        log.info("光照灯关");
    }
}


//域取值
//
//        下表为Cron表达式中六个域能够取的值以及支持的特殊字符。
//        域 	是否必需 	取值范围 	特殊字符
//        秒 	是 	[0, 59] 	* , - /
//        分钟 	是 	[0, 59] 	* , - /
//        小时 	是 	[0, 23] 	* , - /
//        日期 	是 	[1, 31] 	* , - / ? L W
//        月份 	是 	[1, 12]或[JAN, DEC] 	* , - /
//        星期 	是 	[1, 7]或[MON, SUN]。若您使用[1, 7]表达方式，1代表星期一，7代表星期日。 	* , - / ? L #
//        年 	否 	[当前年份，2099] 	* , - /
//        特殊字符
//
//        Cron表达式中的每个域都支持一定数量的特殊字符，每个特殊字符有其特殊含义。
//        特殊字符 	含义 	示例
//        * 	所有可能的值。 	在月域中，*表示每个月；在星期域中，*表示星期的每一天。
//        , 	列出枚举值。 	在分钟域中，5,20表示分别在5分钟和20分钟触发一次。
//        - 	范围。 	在分钟域中，5-20表示从5分钟到20分钟之间每隔一分钟触发一次。
//        / 	指定数值的增量。 	在分钟域中，0/15表示从第0分钟开始，每15分钟。在分钟域中3/20表示从第3分钟开始，每20分钟。
//        ? 	不指定值，仅日期和星期域支持该字符。 	当日期或星期域其中之一被指定了值以后，为了避免冲突，需要将另一个域的值设为?。
//        L 	单词Last的首字母，表示最后一天，仅日期和星期域支持该字符。
//        说明 指定L字符时，避免指定列表或者范围，否则，会导致逻辑问题。
//
//
//        在日期域中，L表示某个月的最后一天。在星期域中，L表示一个星期的最后一天，也就是星期日（SUN）。
//        如果在L前有具体的内容，例如，在星期域中的6L表示这个月的最后一个星期六。
//
//        W 	除周末以外的有效工作日，在离指定日期的最近的有效工作日触发事件。W字符寻找最近有效工作日时不会跨过当前月份，连用字符LW时表示为指定月份的最后一个工作日。 	在日期域中5W，如果5日是星期六，则将在最近的工作日星期五，即4日触发。如果5日是星期天，则将在最近的工作日星期一，即6日触发；如果5日在星期一到星期五中的一天，则就在5日触发。
//        # 	确定每个月第几个星期几，仅星期域支持该字符。 	在星期域中，4#2表示某月的第二个星期四。
//        取值示例
//
//        以下为Cron表达式的取值示例。
//        示例 	说明
//        0 15 10 ? * * 	每天上午10:15执行任务
//        0 15 10 * * ? 	每天上午10:15执行任务
//        0 0 12 * * ? 	每天中午12:00执行任务
//        0 0 10,14,16 * * ? 	每天上午10:00点、下午14:00以及下午16:00执行任务
//        0 0/30 9-17 * * ? 	每天上午09:00到下午17:00时间段内每隔半小时执行任务
//        0 * 14 * * ? 	每天下午14:00到下午14:59时间段内每隔1分钟执行任务
//        0 0-5 14 * * ? 	每天下午14:00到下午14:05时间段内每隔1分钟执行任务
//        0 0/5 14 * * ? 	每天下午14:00到下午14:55时间段内每隔5分钟执行任务
//        0 0/5 14,18 * * ? 	每天下午14:00到下午14:55、下午18:00到下午18:55时间段内每隔5分钟执行任务
//        0 0 12 ? * WED 	每个星期三中午12:00执行任务
//        0 15 10 15 * ? 	每月15日上午10:15执行任务
//        0 15 10 L * ? 	每月最后一日上午10:15执行任务
//        0 15 10 ? * 6L 	每月最后一个星期六上午10:15执行任务
//        0 15 10 ? * 6#3 	每月第三个星期六上午10:15执行任务
//        0 10,44 14 ? 3 WED 	每年3月的每个星期三下午14:10和14:44执行任务
//        0 15 10 ? * * 2022 	2022年每天上午10:15执行任务
//        0 15 10 ? * * * 	每年每天上午10:15执行任务
//        0 0/5 14,18 * * ? 2022 	2022年每天下午14:00到下午14:55、下午18:00到下午18:55时间段内每隔5分钟执行任务
//        0 15 10 ? * 6#3 2022,2023 	2022年至2023年每月第三个星期六上午10:15执行任务
//        0 0/30 9-17 * * ? 2022-2025 	2022年至2025年每天上午09:00到下午17:30时间段内每隔半小时执行任务
//        0 10,44 14 ? 3 WED 2022/2 	从2022年开始，每隔两年3月的每个星期三下午14:10和14:44执行任务
package com.migu.schedule;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.migu.schedule.constants.ReturnCodeKeys;
import com.migu.schedule.info.TaskInfo;

public class ScheduleTest
{
    /**
     * TaskSchedule实例
     */
    private Schedule schedule = new Schedule();
    
    /** 测试调度方案是否符合
     * 
     * @param expecteds 期望的测试结果
     * @param actual 实际返回结果
     */
    void assertPlanEqual(int expecteds[][], List<TaskInfo> actual)
    {
        Assert.assertEquals(expecteds.length, actual.size());
        
        for (int i = 0; i < actual.size(); i++)
        {
            Assert.assertEquals(expecteds[i][0], actual.get(i).getTaskId());
            Assert.assertEquals(expecteds[i][1], actual.get(i).getNodeId());
        }
    }
    
    @Test
    public void testInit()
    {
        int actual = schedule.init();
        Assert.assertEquals(ReturnCodeKeys.E001, actual);
    }
    
    
    
    @Test
    public void testScheduleTask1()
    {
        int actual = schedule.init();
        actual = schedule.registerNode(1);
        actual = schedule.registerNode(3);
        
        actual = schedule.addTask(1, 30);
        actual = schedule.addTask(2, 30);
        actual = schedule.addTask(3, 30);
        actual = schedule.addTask(4, 30);
        
        actual = schedule.scheduleTask(10);
        
        Assert.assertEquals(ReturnCodeKeys.E013, actual);
        
        List<TaskInfo> tasks = new ArrayList<TaskInfo>();
        
        actual = schedule.queryTaskStatus(tasks);
        
        Assert.assertEquals(ReturnCodeKeys.E015, actual);
        
        int expecteds[][] = { 
            {1, 1}, 
            {2, 1}, 
            {3, 3}, 
            {4, 3}};
        
        assertPlanEqual(expecteds, tasks);
    }
}

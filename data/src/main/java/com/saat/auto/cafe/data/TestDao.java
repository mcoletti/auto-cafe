package com.saat.auto.cafe.data;

import com.saat.auto.cafe.common.entitys.TestModel;

/**
 * Created by mcoletti on 6/1/16.
 */
public class TestDao {

    private TestModel testModel;

    public TestModel getTestModel() {

        testModel = new TestModel();
        testModel.setTest("1");

        return testModel;
    }
}

package com.saat.auto.cafe.common.entitys;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by mcoletti on 6/1/16.
 */
public class TestModelTest {

    @Test
    public void testGetTest() throws Exception {
        TestModel result = getTestModel();
        assertThat(result.getTest()).isEqualTo("1");
    }

    @Test
    public void testGetTest2() throws Exception {
        TestModel result = getTestModel();
        assertThat(result.getTest2()).isEqualTo("2");
    }

    @Test
    public void testSetTest() throws Exception {
        TestModel result = getTestModel();

        result.setTest("3");
        assertThat(result.getTest()).isEqualTo("3");

    }

    @Test
    public void testSetTest2() throws Exception {

    }

    private TestModel getTestModel(){

        TestModel testModel = new TestModel();
        testModel.setTest("1");
        testModel.setTest2("2");
        return testModel;
    }
}

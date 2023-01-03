package Selenide.Selenide_3;

import Selenide.Selenide_1.BaseTest;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class HhTest extends BaseTest {

    private final static String URL = "https://hh.ru/applicant/resumes/view?resume=1edf0c93ff095811d20039ed1f6a3638497073";

    @Test
    public void checkAttributeHashMap() {
        HhResumePage hhResumePage = new HhResumePage(URL);

        Map<String, Object> expectedAttributes = new HashMap<>();
        expectedAttributes.put(HhResumePage.GENDER, "М");
        expectedAttributes.put(HhResumePage.AGE, 27);
        expectedAttributes.put(HhResumePage.CITY, "Санкт-Петербург");
        expectedAttributes.put(HhResumePage.CONFIRMED_PHONE, true);
        expectedAttributes.put(HhResumePage.RELOCATE, false);

        Map<String, Object> actualAttributes = hhResumePage.getAttributes();

        Assert.assertEquals(expectedAttributes, actualAttributes);


    }

    @Test
    public void checkAttributesClass() {
        HhResumePage hhResumePage = new HhResumePage(URL);

        Resume expectedAttributes = new Resume("М", 27, "Санкт-Петербург", true, false);
        Resume actualAttributes = new Resume(hhResumePage.getGenderHard(), hhResumePage.getAge(), hhResumePage.getCity(),
                hhResumePage.isPhoneConfirmed(), hhResumePage.isReadyToRelocate());

        //Assert.assertTrue(EqualsBuilder.reflectionEquals(expectedAttributes, actualAttributes));// 1-ый способ сравнения

        Assert.assertEquals(expectedAttributes.getGender(), actualAttributes.getGender());//2-ой способ
        Assert.assertEquals(expectedAttributes.getAge(), actualAttributes.getAge());
        Assert.assertEquals(expectedAttributes.getCity(), actualAttributes.getCity());
        Assert.assertEquals(expectedAttributes.isNumberConfirmed(), actualAttributes.isNumberConfirmed());
        Assert.assertEquals(expectedAttributes.isReadyToRelocate(), actualAttributes.isReadyToRelocate());


    }
}

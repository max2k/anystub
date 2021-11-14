package org.anystub;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RandomGeneratorTest {
    static public enum En {
        Test,
        Prod;


    }

    static public class A {
        int i;
        Integer II;
        Double d;
        String s;
        String[] sa;
        List<String> ls;
        B b;
        List<B> bl;
        En en;
        LocalDate localDate;
        LocalDateTime localDateTime;
        OffsetTime offsetTime;
        OffsetDateTime offsetDateTime;
        ZonedDateTime zonedDateTime;

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        public Integer getII() {
            return II;
        }

        public void setII(Integer II) {
            this.II = II;
        }

        public Double getD() {
            return d;
        }

        public void setD(Double d) {
            this.d = d;
        }

        public String getS() {
            return s;
        }

        public void setS(String s) {
            this.s = s;
        }

        public String[] getSa() {
            return sa;
        }

        public void setSa(String[] sa) {
            this.sa = sa;
        }

        public List<String> getLs() {
            return ls;
        }

        public void setLs(List<String> ls) {
            this.ls = ls;
        }

        public B getB() {
            return b;
        }

        public void setB(B b) {
            this.b = b;
        }

        public List<B> getBl() {
            return bl;
        }

        public void setBl(List<B> bl) {
            this.bl = bl;
        }

        public En getEn() {
            return en;
        }

        public void setEn(En en) {
            this.en = en;
        }

        public LocalDate getLocalDate() {
            return localDate;
        }

        public void setLocalDate(LocalDate localDate) {
            this.localDate = localDate;
        }

        public LocalDateTime getLocalDateTime() {
            return localDateTime;
        }

        public void setLocalDateTime(LocalDateTime localDateTime) {
            this.localDateTime = localDateTime;
        }

        public OffsetTime getOffsetTime() {
            return offsetTime;
        }

        public void setOffsetTime(OffsetTime offsetTime) {
            this.offsetTime = offsetTime;
        }

        public OffsetDateTime getOffsetDateTime() {
            return offsetDateTime;
        }

        public void setOffsetDateTime(OffsetDateTime offsetDateTime) {
            this.offsetDateTime = offsetDateTime;
        }

        public ZonedDateTime getZonedDateTime() {
            return zonedDateTime;
        }

        public void setZonedDateTime(ZonedDateTime zonedDateTime) {
            this.zonedDateTime = zonedDateTime;
        }
    }

    static public class B {
        int val;

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }
    }

    @Test
    void t1() throws JsonProcessingException {

        List list = ObjectMapperFactory.get().readValue("[]", List.class);
        System.out.println(list);

    }

    @Test
    void t2() throws JsonProcessingException {

        A a = RandomGenerator.g(A.class);
        String s = ObjectMapperFactory.get().writeValueAsString(a);

        System.out.println(s);
    }

    @Test
    void enumTest() {
        En en = RandomGenerator.gEnum(En.class);
        System.out.println(en);
    }
}
package org.anystub;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    void t1() throws JsonProcessingException, InvocationTargetException, IllegalAccessException {
        String s;
        s = ObjectMapperFactory.get().writeValueAsString(123);

        ObjectMapperFactory.get().readValue(s, Integer.class);

        String[] sa = new String[]{"123", "543"};
        s = ObjectMapperFactory.get().writeValueAsString(sa);
        A a = ObjectMapperFactory.get().readValue("{}", A.class);

        Class<? extends A> aClass = a.getClass();
        for (Method m : aClass.getMethods()) {
            if (!m.getName().startsWith("set")) {
                continue;
            }
//            System.out.println(m.getName());
            System.out.println(m.getParameterTypes()[0]);
            Class<?> parameterType = m.getParameterTypes()[0];
            if (parameterType.isArray()) {
                Object o = ObjectMapperFactory.get().readValue("[]", parameterType);
                m.invoke(a, o);
                continue;
            }

            if (parameterType.getName() == "int") {
                Integer v = 1;
                m.invoke(a, v);
                continue;
            }
//            System.out.println(m.getReturnType().getName());
        }

        System.out.println(ObjectMapperFactory.get().writeValueAsString(a));

    }

    @Test
    void t2() throws JsonProcessingException {
        Integer g = RandomGenerator.g(int.class);
        System.out.println(g);
        Double gd = RandomGenerator.g(double.class);
        System.out.println(gd);
        String g1 = RandomGenerator.g(String.class);
        System.out.println(g1);

        A a = RandomGenerator.g(A.class);
        System.out.println(ObjectMapperFactory.get().writeValueAsString(a));

    }

    @Test
    void enumTest() {
        En en = RandomGenerator.gEnum(En.class);
        System.out.println(en);
    }
}
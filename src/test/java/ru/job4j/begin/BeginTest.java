package ru.job4j.begin;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class BeginTest {

    @Test
    public void getI() {
        Assert.assertThat(new Begin().getI(), is(1));
    }
}
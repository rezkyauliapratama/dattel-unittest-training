package android.rezkyauliapratama.com.unittesttraining;

import android.rezkyauliapratama.com.unittesttraining.util.TimeUtil;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Date;

public class TimeUTilityTest {

    private final String STR_DATE = "2018-10-08";

    @Rule
    MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    TimeUtil SUT;

    final Date date = Mockito.mock(Date.class);

    @Before
    public void setUp() throws Exception {
        Mockito.when(date.getTime()).thenReturn(30L);
        correctDate();
    }

    @Test
    public void name() {
        ArgumentCaptor<String> ac  = ArgumentCaptor.forClass(String.class);

        SUT.convertStringToDate(STR_DATE);

    }

    private void correctDate() {

        Mockito.when(SUT.convertStringToDate(ArgumentMatchers.any(String.class))).thenReturn(date);



    }
}

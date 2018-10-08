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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TimeUTilityTest {

    private final String STR_DATE = "2018-10-08";

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    SimpleDateFormat simpleDateFormat;

    private TimeUtil SUT;

    private Date date;

    @Before
    public void setUp() throws Exception {
        SUT = new TimeUtil(simpleDateFormat);
        date = new Date();/*Mockito.mock(Date.class);*/
        correctDate();
    }

    @Test
    public void name() {
        ArgumentCaptor<String> ac  = ArgumentCaptor.forClass(String.class);
        Date result = SUT.convertStringToDate(STR_DATE);
        assertThat(result, is(date));

    }

    private void correctDate() throws ParseException {

        Mockito.when(simpleDateFormat.parse(ArgumentMatchers.any(String.class))).thenReturn(date);

    }
}

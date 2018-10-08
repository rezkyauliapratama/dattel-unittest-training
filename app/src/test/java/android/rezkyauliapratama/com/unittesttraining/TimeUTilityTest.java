package android.rezkyauliapratama.com.unittesttraining;

import android.rezkyauliapratama.com.unittesttraining.util.TimeUtil;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

public class TimeUTilityTest {

    private final String STR_DATE = "2018-10-08";

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    SimpleDateFormat simpleDateFormatMock;

    private TimeUtil SUT;

    private Date date;

    @Before
    public void setUp() throws Exception {
        SUT = new TimeUtil(simpleDateFormatMock);
        date = new Date();/*Mockito.mock(Date.class);*/
        correctDate();
    }

    @Test
    public void convertStringToDate_success_returnCorrectDate() {
        Date result = SUT.convertStringToDate(STR_DATE);
        assertThat(result, is(date));

    }


    @Test
    public void convertStringToDate_success_stringNeedToConvertPassedToFunction() throws ParseException {
        ArgumentCaptor<String> ac = ArgumentCaptor.forClass(String.class);
        SUT.convertStringToDate(STR_DATE);
        verify(simpleDateFormatMock).parse(ac.capture());
        String result = ac.getValue();
        assertThat(result, is(STR_DATE));
    }

    private void correctDate() throws ParseException {
        Mockito.when(simpleDateFormatMock.parse(ArgumentMatchers.any(String.class))).thenReturn(date);
    }
}

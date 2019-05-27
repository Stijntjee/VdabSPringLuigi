package be.vdab.luigi.services;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import be.vdab.luigi.restclients.koersClient;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

// enkele andere imports
@RunWith(MockitoJUnitRunner.class)
public class EuroServiceTest
{
    @Mock
    private koersClient koersClient;
    private EuroService euroService;

    @Before
    public void before() {
        when(koersClient.getDollarKoers()).thenReturn(BigDecimal.valueOf(1.5));
        euroService = new DefaultEuroService(new koersClient[] {koersClient});
    }

    @Test
        public void naarDollar () {
            assertThat(euroService.naarDollar(BigDecimal.valueOf(2)))
                    .isEqualByComparingTo("3");
            verify(koersClient).getDollarKoers();
        }
    }
package be.vdab.luigi.services;

import be.vdab.luigi.exceptions.KoersClientException;
import be.vdab.luigi.restclients.koersClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
class DefaultEuroService implements EuroService {
    private final koersClient[] koersClients;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //CONSTRUCTORS

   /* DefaultEuroService(@Qualifier("Fixer") koersClient koersClient)
    {
        this.koersClient = koersClient;
    }*/

    DefaultEuroService(koersClient[] koersClients)
    {
        this.koersClients = koersClients;
    }

    //!FIXERKOERSCLIENT OP WEBSITE  |  DUMMY OP TEST!
    @Override
    public BigDecimal naarDollar(BigDecimal euro)
    {
        for (koersClient koersClient : koersClients)
        {
            try {
                return euro.multiply(koersClient.getDollarKoers()).setScale(2, RoundingMode.HALF_UP);
            }
            catch (KoersClientException ex) {
                logger.error("kan dollar koers niet lezen", ex);
            }
        }
        logger.error("kan dollar koers van geen enkele bron lezen");
        return null;
    }
}

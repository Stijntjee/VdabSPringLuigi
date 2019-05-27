package be.vdab.luigi.restclients;

import be.vdab.luigi.exceptions.KoersClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

@Component
@Order(1)
class FixerKoersClient implements koersClient
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    // importeer Logger en LoggerFactory uit org.slf4j
    private final URL url;

    //CONSTRUCTORS
    FixerKoersClient(@Value("${fixerKoersURL}") URL url)
    {
        this.url = url;
    }

    @Override
    public BigDecimal getDollarKoers() {
        try (Scanner scanner = new Scanner(url.openStream()))
        {
            String lijn = scanner.nextLine();
            int beginPositieKoers = lijn.indexOf("USD") + 5;
            int accoladePositie = lijn.indexOf('}', beginPositieKoers);
            logger.info("koers gelezen via Fixer");
            return new BigDecimal(lijn.substring(beginPositieKoers, accoladePositie));
        }
        catch (IOException | NumberFormatException ex)
        {
            String fout = "kan koers niet lezen via Fixer";
            logger.error(fout, ex);
            throw new KoersClientException(fout);
        }
    }
}


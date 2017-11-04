package com.oakinvest.b2g;

import com.oakinvest.b2g.dto.ext.bitcoin.bitcoind.getblockcount.GetBlockCountResponse;
import com.oakinvest.b2g.repository.bitcoin.BitcoinBlockRepository;
import com.oakinvest.b2g.service.BitcoindService;
import com.oakinvest.b2g.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

import static com.oakinvest.b2g.domain.bitcoin.BitcoinBlockState.BLOCK_FULLY_IMPORTED;

/**
 * Application launcher.
 *
 * @author straumat
 */
@SpringBootApplication
@ComponentScan
public class Application extends SpringBootServletInitializer {

    /**
     * Bitcoind service.
     */
    @Autowired
    private BitcoindService bds;

    /**
     * Bitcoin block repository.
     */
    @Autowired
    private BitcoinBlockRepository bbr;

    /**
     * Status service.
     */
    @Autowired
    private StatusService status;

    /**
     * Application launcher.
     *
     * @param args parameters.
     */
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected final SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    /**
     * Application initialization.
     */
    @PostConstruct
    public final void initApplication() {
        // Update the status of the number of block imported.
        status.setImportedBlockCount(bbr.countBlockByState(BLOCK_FULLY_IMPORTED));

        // Update the status of the number of block in bitcoind.
        GetBlockCountResponse getBlockCountResponse = bds.getBlockCount();
        if (getBlockCountResponse.getError() != null) {
            status.setTotalBlockCount(getBlockCountResponse.getResult());
        }
    }

}

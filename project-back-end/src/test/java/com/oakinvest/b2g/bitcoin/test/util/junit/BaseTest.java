package com.oakinvest.b2g.bitcoin.test.util.junit;

import com.oakinvest.b2g.bitcoin.Application;
import com.oakinvest.b2g.bitcoin.batch.ImportBatch;
import com.oakinvest.b2g.bitcoin.test.util.mock.BitcoinCoreMock;
import com.oakinvest.b2g.bitcoin.repository.AddressRepository;
import com.oakinvest.b2g.bitcoin.repository.BlockRepository;
import com.oakinvest.b2g.bitcoin.repository.TransactionInputRepository;
import com.oakinvest.b2g.bitcoin.repository.TransactionOutputRepository;
import com.oakinvest.b2g.bitcoin.repository.TransactionRepository;
import com.oakinvest.b2g.bitcoin.service.BitcoinDataService;
import com.oakinvest.b2g.bitcoin.service.BitcoinCoreService;
import com.oakinvest.b2g.bitcoin.util.buffer.BitcoinDataServiceBuffer;
import org.junit.runner.RunWith;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.PostConstruct;

/**
 * Base for test.
 */
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public abstract class BaseTest {

    /**
     * Parameter for live test.
     */
    private static final String PARAMETER_LIVE = "live";

    /**
     * Parameter expected for live test
     */
    private static final String PARAMETER_LIVE_VALUE = "true";

    /**
     * Bitcoin address repository.
     */
    @Autowired
    private AddressRepository addressRepository;

    /**
     * Bitcoin block repository.
     */
    @Autowired
    private BlockRepository blockRepository;

    /**
     * Bitcoin transaction repository.
     */
    @Autowired
    private TransactionRepository transactionRepository;

    /**
     * Transaction import repository.
     */
    @Autowired
    private TransactionInputRepository transactionInputRepository;

    /**
     * Transaction output repository.
     */
    @Autowired
    private TransactionOutputRepository transactionOutputRepository;

    /**
     * Bitcoin core mock.
     */
    @Autowired
    private BitcoinCoreMock bitcoinCoreMock;

    /**
     * Import batch.
     */
    @Autowired
    private ImportBatch batchBlocks;

    /**
     * Buffer store.
     */
    @Autowired
    private BitcoinDataServiceBuffer buffer;

    /**
     * Bitcoin core service.
     */
    @Autowired
    private BitcoinCoreService bitcoinCoreService;

    /**
     * Session factory.
     */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * If we have the profile "live" active, we delete the cached data from core.
     */
    @PostConstruct
    public void deleteCache() {
        if (PARAMETER_LIVE_VALUE.equals(System.getProperty(PARAMETER_LIVE))) {
            getBitcoinCoreMock().deleteCache();
        }
    }

    /**
     * Getter bds.
     *
     * @return bds
     */
    protected final BitcoinCoreService getBitcoinCoreService() {
        return bitcoinCoreService;
    }

    /**
     * Getter batchBlocks.
     *
     * @return batchBlocks
     */
    protected final ImportBatch getBatchBlocks() {
        return batchBlocks;
    }

    /**
     * Getter buffer.
     *
     * @return buffer
     */
    protected final BitcoinDataServiceBuffer getBuffer() {
        return buffer;
    }

    /**
     * Getter sessionFactory.
     *
     * @return sessionFactory
     */
    protected final SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Getter addressRepository.
     *
     * @return addressRepository
     */
    protected final AddressRepository getAddressRepository() {
        return addressRepository;
    }

    /**
     * Getter transactionRepository.
     *
     * @return transactionRepository
     */
    protected final TransactionRepository getTransactionRepository() {
        return transactionRepository;
    }

    /**
     * Getter transactionInputRepository.
     *
     * @return transactionInputRepository
     */
    protected final TransactionInputRepository getTransactionInputRepository() {
        return transactionInputRepository;
    }

    /**
     * Getter transactionOutputRepository.
     *
     * @return transactionOutputRepository
     */
    protected final TransactionOutputRepository getTransactionOutputRepository() {
        return transactionOutputRepository;
    }

    /**
     * Getter bitcoin core mock.
     *
     * @return bitcoinCoreMock
     */
    protected final BitcoinCoreMock getBitcoinCoreMock() {
        return bitcoinCoreMock;
    }

    /**
     * Getter bitcoinBlockRepository.
     *
     * @return bitcoinBlockRepository
     */
    protected final BlockRepository getBlockRepository() {
        return blockRepository;
    }

}

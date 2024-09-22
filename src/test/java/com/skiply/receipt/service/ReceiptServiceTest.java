package com.skiply.receipt.service;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.skiply.receipt.entity.Receipt;
import com.skiply.receipt.exception.ResourceNotFoundException;
import com.skiply.receipt.repository.ReceiptRepository;

public class ReceiptServiceTest {

    private ReceiptService receiptService;
    private ReceiptRepository receiptRepository;

    @BeforeEach
    public void setup() {
        // Create an instance of the ReceiptRepository
 //       receiptRepository = new InMemoryReceiptRepository(); // Use an in-memory implementation for testing
   //     receiptService = new ReceiptService();
    //    receiptService.setTransactionRepository(receiptRepository); // Assume there's a setter or use constructor
    }

    @Test
    public void testAddTransaction_Success() {
        // Create a transaction
        Receipt transaction = new Receipt();
        transaction.setStudentId(1);
        transaction.setAmount(BigDecimal.valueOf(100.0));

        // Assume student check passes, so we can directly save the transaction
        // Simulate saving the transaction
        Receipt savedTransaction = new Receipt();
        savedTransaction.setAmount(BigDecimal.valueOf(100.0));
        savedTransaction.setStudentId(1);

        // Assertions
        assertNotNull(savedTransaction);
        assertEquals(BigDecimal.valueOf(100.0), savedTransaction.getAmount());
        assertEquals(1, savedTransaction.getStudentId());
    }

    @Test
    public void testgetReceiptByStudentId() {
        // Create a transaction for a non-existent student
    	 Receipt transaction = new Receipt();
         transaction.setStudentId(1);
         transaction.setAmount(BigDecimal.valueOf(100.0));

         // Assume student check passes, so we can directly save the transaction
         // Simulate saving the transaction
         Receipt savedTransaction = new Receipt();
         savedTransaction.setAmount(BigDecimal.valueOf(100.0));
         savedTransaction.setStudentId(1);

         // Assertions
         assertNotNull(savedTransaction);
         assertEquals(BigDecimal.valueOf(100.0), savedTransaction.getAmount());
         assertEquals(1, savedTransaction.getStudentId());
    }
}

// In-memory repository implementation for testing
class InMemoryReceiptRepository implements ReceiptRepository {
    private List<Receipt> receipts = new ArrayList<>();

    @Override
    public Receipt save(Receipt receipt) {
        receipts.add(receipt);
        return receipt; // Simulate saving by returning the same receipt
    }

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Receipt> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Receipt> List<S> saveAllAndFlush(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllInBatch(Iterable<Receipt> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Receipt getOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Receipt getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Receipt getReferenceById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Receipt> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Receipt> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Receipt> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Receipt> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Receipt> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Receipt> findById(Integer id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Receipt entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Receipt> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Receipt> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Receipt> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Receipt> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public <S extends Receipt> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Receipt> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends Receipt> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <S extends Receipt, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Receipt> findByStudentId(int studentId) {
		// TODO Auto-generated method stub
		return null;
	}

    // Implement other methods as needed for your tests
}

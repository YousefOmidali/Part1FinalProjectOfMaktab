package service;

import entity.Wallet;
import repository.WalletRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class WalletService {
    WalletRepository walletRepository = new WalletRepository();

    public void save(Wallet wallet) {
        walletRepository.save(wallet);
    }

    public void deleteById(Long id) {
        walletRepository.deleteById(id);
    }

    public void update(Wallet wallet) {
        walletRepository.update(wallet);
    }

    public Wallet findById(Long id) {
        return walletRepository.findById(id);
    }

    public List<Wallet> findAll() {
        return walletRepository.findAll();
    }
}

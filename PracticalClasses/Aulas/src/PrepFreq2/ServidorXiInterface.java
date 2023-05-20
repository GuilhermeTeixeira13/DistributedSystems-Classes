package PrepFreq2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServidorXiInterface extends Remote {
    public String lerConteudoArquivo() throws RemoteException;
}

package Lab6;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.*;
import java.security.cert.CertificateException;

/**
 * Created by Fredrik on 2015-03-05.
 */
public class Server {
    public static void main(String[] args){
        SSLServerSocketFactory ssf =
                (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
        System.out.println("Stöder:");
        //for(int i = 0; i < ssf.getSupportedCipherSuites().length; i++)
        //    System.out.println(ssf.getSupportedCipherSuites()[i]);
        //SSLServerSocket ss = null;
        try {
            char[] keyPassword =  "bullar".toCharArray();
            FileInputStream keyFile = new FileInputStream(".keystore");


            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(keyFile, keyPassword);
            // init KeyManagerFactory
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(keyStore, keyPassword);
            // init KeyManager
            KeyManager keyManagers[] = keyManagerFactory.getKeyManagers();

            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();



            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(keyManagers,trustManagers,new SecureRandom());
            SSLServerSocketFactory socketFactory = sslContext.getServerSocketFactory();

            ServerSocket serverSocket = socketFactory.createServerSocket(443);
            Socket s = serverSocket.accept();


            /*ss = (SSLServerSocket)ssf.createServerSocket(1239);
            String[] cipher = {"SSL_DH_anon_WITH_RC4_128_MD5"};
            ss.setEnabledCipherSuites(cipher);
            System.out.println("Vald:");
            for(int i = 0; i < ss.getEnabledCipherSuites().length; i++)
                System.out.println(ss.getEnabledCipherSuites()[i]);
            SSLSocket s = (SSLSocket)ss.accept();*/

            BufferedReader infil =
                    new BufferedReader(new InputStreamReader(s.getInputStream()));
            String rad = null;
            while( (rad=infil.readLine()) != null)
                System.out.println(rad);
            infil.close();
        }
        catch(IOException e){
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        }
    }
}


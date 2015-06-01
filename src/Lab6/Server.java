package Lab6;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.security.*;
import java.security.cert.CertificateException;

public class Server {
    public static void main(String[] args){
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


            SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
            sslContext.init(keyManagers,trustManagers,new SecureRandom());
            SSLServerSocketFactory socketFactory = sslContext.getServerSocketFactory();

            SSLServerSocket ss = (SSLServerSocket) socketFactory.createServerSocket(1234);
            ss.setEnabledCipherSuites(socketFactory.getSupportedCipherSuites());

            SSLSocket s = (SSLSocket) ss.accept();
            s.startHandshake();


            PrintStream response =
                    new PrintStream(s.getOutputStream());
            response.println("HTTP/1.1 200 OK");
            response.println("Server : Slask 0.1 Beta");

            response.println("Content-Type: text/html");
            response.println();
            response.println("Hello world!");

            s.close();
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


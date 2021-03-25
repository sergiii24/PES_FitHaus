package cat.fib.fithaus.api;

import android.net.SSLCertificateSocketFactory;

import com.android.volley.VolleyLog;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

class ClientSSLSocketFactory  extends SSLSocketFactory {

    private static final String TAG = ClientSSLSocketFactory.class.getSimpleName();

    private static ClientSSLSocketFactory instance;

    private SSLSocketFactory socketFactory;
    private X509TrustManager trustManager;
    private boolean isSecured = Boolean.TRUE;
    private String[] publicKeys;
    SSLContext sslContext = SSLContext.getInstance("TLS");

    private ClientSSLSocketFactory( ) throws NoSuchAlgorithmException {
    }

    static ClientSSLSocketFactory sslSocketFactory() throws NoSuchAlgorithmException {
        if (instance == null)
            instance = new ClientSSLSocketFactory();
        return instance;
    }

    void init(boolean isSecured, String[] publicKeys) {
        this.isSecured = isSecured;
        this.publicKeys = publicKeys;
    }

    SSLSocketFactory getFactory() {
        if (socketFactory == null) {
            try {
                X509TrustManager tm = new X509TrustManager() {
                    public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {}

                    public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {}

                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                };
                SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(null, new TrustManager[]{tm}, null);
                socketFactory = sslContext.getSocketFactory();
            } catch (NoSuchAlgorithmException | KeyManagementException e) {
                VolleyLog.e(TAG, "Unable to create the ssl socket factory.");
                return SSLCertificateSocketFactory.getDefault(0, null);
            }
        }
        return socketFactory;
    }


    @Override
    public String[] getDefaultCipherSuites() {
        return new String[0];
    }

    @Override
    public String[] getSupportedCipherSuites() {
        return new String[0];
    }

    @Override
    public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException, UnknownHostException {
        return sslContext.getSocketFactory().createSocket(socket, host, port, autoClose);
    }

    @Override
    public Socket createSocket() throws IOException {
        return sslContext.getSocketFactory().createSocket();
    }

    @Override
    public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
        return null;
    }

    @Override
    public Socket createSocket(String host, int port, InetAddress localHost, int localPort) throws IOException, UnknownHostException {
        return null;
    }

    @Override
    public Socket createSocket(InetAddress host, int port) throws IOException {
        return null;
    }

    @Override
    public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) throws IOException {
        return null;
    }
}
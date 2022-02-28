package modulos.produto;


import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import telas.LoginTela;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@DisplayName("Testes mobile do modulo de produto")
public class ProdutoTest {

    private WebDriver app;
    @BeforeEach
    public void beforeEach() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName","Pixel 3 ptqs");
        capabilities.setCapability("platform", "Android");
        capabilities.setCapability("appPackage","com.lojinha");
        capabilities.setCapability("appActivity","com.lojinha.ui.MainActivity");
        capabilities.setCapability("app","C:\\Users\\guilherme.marinho\\Downloads\\JL - curso\\mobile\\Lojinha Nativa\\lojinha-nativa.apk");

        app = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        app.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    @AfterEach
    public void afterEach(){
        app.quit();
    }
    @DisplayName("Validacao do valor de produto nao permitido")
    @Test
    public void testValidacaoDoValorDeProdutoNaoPermitido(){
        String mensagemApresentada = new LoginTela(app)
                .preencherUsuario("admin")
                .preencherSenha("admin")
                .submerterLogin()
                .abrirTelaAdicaoProduto()
                .preenhcerNomeProduto("Iphone")
                .preencherValorProduto("700001")
                .preencherCoresProduto("Preto")
                .submeterComErro()
                .obterMensagemComErro();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00",mensagemApresentada);

    }
}

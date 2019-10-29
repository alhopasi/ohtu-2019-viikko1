package ohtu.ohtuvarasto;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenLisaaminenPalaa() {
        varasto.lisaaVarastoon(-2);
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void liikaaLisaaminenTayttaa() {
        varasto.lisaaVarastoon(12);
        assertEquals(0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenOttaminenPalaa() {
        varasto.otaVarastosta(-1);
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void liikaaOttaminenAntaaKaikki() {
        varasto.lisaaVarastoon(6);
        double saatuMaara = varasto.otaVarastosta(10);
        assertEquals(6, saatuMaara, vertailuTarkkuus);
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void toStringToimii() {
        varasto.lisaaVarastoon(3);
        assertEquals("saldo = 3.0, vielä tilaa 7.0", varasto.toString());
    }
    
    @Test
    public void negatiivinenTilavuusAntaaNollavaraston() {
        Varasto va = new Varasto(-2);
        assertEquals(0.0, va.getTilavuus(), vertailuTarkkuus);
        Varasto var = new Varasto(-2, 4);
        assertEquals(0.0, var.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriToimiiAlkuSaldolla() {
        Varasto va = new Varasto(10, 3);
        assertEquals(2, va.getSaldo(), vertailuTarkkuus);
        assertEquals(7, va.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenAlkuSaldoAntaaNollasaldon() {
        Varasto va = new Varasto(10, -5);
        assertEquals(0, va.getSaldo(), vertailuTarkkuus);
        assertEquals(10, va.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    

}
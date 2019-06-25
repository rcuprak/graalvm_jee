package net.cuprak.graalvm;

import net.cuprak.graalvm.model.RaceParticipant;
import net.cuprak.graalvm.model.RoadRace;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.List;

/**
 * Tests the RaceBean
 * @author Ryan Cuprak
 */
@RunWith(Arquillian.class)
public class TestRaceBean {

    @Inject
    private RaceBean raceBean;

    @Inject
    private DataCleanupBean cleanupBean;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addPackage("net.cuprak.graalvm.model")
                .addPackage("net.cuprak.graalvm")
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml");
    }

    @Test
    public void testCreateRace() {
        RoadRace chaseCorporateChallenge = new RoadRace("Bedrock Road Race",3.5);
        chaseCorporateChallenge.addParticipant(new RaceParticipant("Barney Rubble",30,20));
        chaseCorporateChallenge.addParticipant(new RaceParticipant("Fred Flintstone",30,80));
        raceBean.createRace(chaseCorporateChallenge);
        List<RoadRace> races = raceBean.getRoadRaces();
        Assert.assertEquals(2,races.size());
    }

    @Test
    public void testCleanup() {
        RoadRace chaseCorporateChallenge = new RoadRace("Bedrock Road Race",3.5);
        chaseCorporateChallenge.addParticipant(new RaceParticipant("Barney Rubble",30,20));
        chaseCorporateChallenge.addParticipant(new RaceParticipant("Fred Flintstone",30,80));
        raceBean.createRace(chaseCorporateChallenge);
        cleanupBean.cleanupData();
    }

}

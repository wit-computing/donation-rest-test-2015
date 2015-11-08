package app.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import app.donation.api.DonationServiceAPI;
import app.donation.model.Donation;
import app.donation.model.User;


public class DonationTest
{
  User marge =  new User ("marge",  "simpson", "homer@simpson.com",  "secret");
  
  DonationServiceAPI serviceAPI = new DonationServiceAPI();
  
  @Before
  public void setup() throws Exception
  { 
    marge = serviceAPI.createUser(marge);
  }
  
  @After
  public void teardown() throws Exception
  {
    serviceAPI.deleteUser(marge);
  }
  
  @Test
  public void testCreateDonation () throws Exception
  {
    Donation donation = new Donation (123, "cash");
    Donation returnedDonation = serviceAPI.createDonation(marge, donation);
    assertEquals (donation, returnedDonation);
    
    serviceAPI.deleteDonation(marge, returnedDonation);
  }
  
  
  @Test
  public void testCreateDonations () throws Exception
  {
    Donation donation1 = new Donation (123, "cash");
    Donation donation2 = new Donation (450, "cash");
    Donation donation3 = new Donation (43,  "paypal");
    
    Donation returnedDonation1 = serviceAPI.createDonation(marge, donation1);
    Donation returnedDonation2 = serviceAPI.createDonation(marge, donation2);
    Donation returnedDonation3 = serviceAPI.createDonation(marge, donation3);
    
    assertEquals(donation1, returnedDonation1);
    assertEquals(donation2, returnedDonation2);
    assertEquals(donation3, returnedDonation3);

    serviceAPI.deleteDonation(marge, returnedDonation1);
    serviceAPI.deleteDonation(marge, returnedDonation2);    
    serviceAPI.deleteDonation(marge, returnedDonation3);
  }
  
  @Test
  public void testListDonations () throws Exception
  {
    Donation donation1 = new Donation (123, "cash");
    Donation donation2 = new Donation (450, "cash");
    Donation donation3 = new Donation (43,  "paypal");
    
    serviceAPI.createDonation(marge, donation1);
    serviceAPI.createDonation(marge, donation2);
    serviceAPI.createDonation(marge, donation3);
    
    List<Donation> donations = serviceAPI.getDonations(marge);
    assertEquals (3, donations.size());
    
    assertTrue(donations.contains(donation1));
    assertTrue(donations.contains(donation2));
    assertTrue(donations.contains(donation3));

    serviceAPI.deleteDonation(marge, donations.get(0));
    serviceAPI.deleteDonation(marge, donations.get(1));    
    serviceAPI.deleteDonation(marge, donations.get(2));
  }
  
}

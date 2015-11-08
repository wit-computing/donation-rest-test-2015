package app.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import app.donation.api.DonationServiceAPI;
import app.donation.model.User;


public class UserTest
{
  static User userArray [] = 
  { 
    new User ("homer",  "simpson", "homer@simpson.com",  "secret"),
    new User ("lisa",   "simpson", "lisa@simpson.com",   "secret"),
    new User ("maggie", "simpson", "maggie@simpson.com", "secret"),
    new User ("bart",   "simpson", "bart@simpson.com",   "secret"),
    new User ("marge",  "simpson", "marge@simpson.com",  "secret"),
  };  
  
  List <User> userList = new ArrayList<>();
  
  DonationServiceAPI serviceAPI = new DonationServiceAPI();
  
  @Before
  public void setup() throws Exception
  { 
    for (User user : userArray)
    {
      User returned = serviceAPI.createUser(user);
      userList.add(returned);
    }
  }
  
  @After
  public void teardown() throws Exception
  {
    for (User user : userList)
    {
      String success = serviceAPI.deleteUser(user);
      assertEquals (success, "success");
    }
  }
  
  @Test
  public void testCreate () throws Exception
  {
    assertEquals (userArray.length, userList.size());
    for (int i=0; i<userArray.length; i++)
    {
      assertEquals(userList.get(i), userArray[i]);
    }
  }

  @Test
  public void testList() throws Exception
  {
    List<User> list = serviceAPI.getUsers();
    assertTrue (list.containsAll(userList));
  }
  
  @Test
  public void testDelete () throws Exception
  {
    List<User> list1 = serviceAPI.getUsers();
    User testUser = new User("mark",  "simpson", "marge@simpson.com",  "secret");
    User returnedUser = serviceAPI.createUser(testUser);
    List<User> list2 = serviceAPI.getUsers();
    assertEquals (list1.size()+1, list2.size());
    serviceAPI.deleteUser(returnedUser);
    List<User> list3 = serviceAPI.getUsers();
    assertEquals (list1.size(), list3.size());
  }
}
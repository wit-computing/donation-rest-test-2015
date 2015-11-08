package app.donation.api;

import java.util.List;


import app.donation.model.Donation;
import app.donation.model.User;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface DonationService
{
  @GET("/api/users")
  Call<List<User>> getUsers();
  
  @POST("/api/users")
  Call<User> createUser(@Body User user);
  
  @DELETE("/api/users/{id}")
  Call<String> deleteUser(@Path("id") Long id);

  @GET("/api/donations")
  Call<List<Donation>> getDonations();

  @POST("/api/donations")
  Call<Donation> createDonation(@Body Donation donation);
}
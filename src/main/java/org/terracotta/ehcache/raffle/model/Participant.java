package org.terracotta.ehcache.raffle.model;

/**
 * @author Alex Snaps
 */
public class Participant {

  private String  firstName;
  private String  lastName;
  private String  company;
  private String  email;
  private boolean atScale;
  private String  title;
  private String  uses;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(final String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(final String lastName) {
    this.lastName = lastName;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(final String company) {
    this.company = company;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  public boolean isAtScale() {
    return atScale;
  }

  public void setAtScale(final boolean atScale) {
    this.atScale = atScale;
  }

  public String getJobTitle() {
    return title;
  }

  public void setJobTitle(final String jobTitle) {
    this.title = jobTitle;
  }

  public String getUses() {
    return uses;
  }

  public void setUses(final String uses) {
    this.uses = uses;
  }

  @Override
  public String toString() {
    return lastName + ", " + firstName + " from " + company; 
  }
}

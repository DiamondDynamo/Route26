/*
 * HCVB-Service API
 * This is a API
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.swagger.client.model;

import java.util.Objects;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.client.model.Location;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.threeten.bp.OffsetDateTime;

/**
 * Event
 */
//Todo: annotation
//@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-04-25T12:47:25.920-04:00")
public class Event {
  @SerializedName("name")
  private String name = null;

  @SerializedName("location")
  private Location location = null;

  @SerializedName("startDatetime")
  private OffsetDateTime startDatetime = null;

  @SerializedName("endDatetime")
  private OffsetDateTime endDatetime = null;

  @SerializedName("description")
  private String description = null;

  @SerializedName("images")
  private List<UUID> images = null;

  @SerializedName("ageFrom")
  private Integer ageFrom = null;

  @SerializedName("ageTo")
  private Integer ageTo = null;

  public Event name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Event location(Location location) {
    this.location = location;
    return this;
  }

   /**
   * Get location
   * @return location
  **/
  @ApiModelProperty(value = "")
  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public Event startDatetime(OffsetDateTime startDatetime) {
    this.startDatetime = startDatetime;
    return this;
  }

   /**
   * Get startDatetime
   * @return startDatetime
  **/
  @ApiModelProperty(value = "")
  public OffsetDateTime getStartDatetime() {
    return startDatetime;
  }

  public void setStartDatetime(OffsetDateTime startDatetime) {
    this.startDatetime = startDatetime;
  }

  public Event endDatetime(OffsetDateTime endDatetime) {
    this.endDatetime = endDatetime;
    return this;
  }

   /**
   * Get endDatetime
   * @return endDatetime
  **/
  @ApiModelProperty(value = "")
  public OffsetDateTime getEndDatetime() {
    return endDatetime;
  }

  public void setEndDatetime(OffsetDateTime endDatetime) {
    this.endDatetime = endDatetime;
  }

  public Event description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(value = "")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Event images(List<UUID> images) {
    this.images = images;
    return this;
  }

  public Event addImagesItem(UUID imagesItem) {
    if (this.images == null) {
      this.images = new ArrayList<UUID>();
    }
    this.images.add(imagesItem);
    return this;
  }

   /**
   * Get images
   * @return images
  **/
  @ApiModelProperty(value = "")
  public List<UUID> getImages() {
    return images;
  }

  public void setImages(List<UUID> images) {
    this.images = images;
  }

  public Event ageFrom(Integer ageFrom) {
    this.ageFrom = ageFrom;
    return this;
  }

   /**
   * Get ageFrom
   * @return ageFrom
  **/
  @ApiModelProperty(value = "")
  public Integer getAgeFrom() {
    return ageFrom;
  }

  public void setAgeFrom(Integer ageFrom) {
    this.ageFrom = ageFrom;
  }

  public Event ageTo(Integer ageTo) {
    this.ageTo = ageTo;
    return this;
  }

   /**
   * Get ageTo
   * @return ageTo
  **/
  @ApiModelProperty(value = "")
  public Integer getAgeTo() {
    return ageTo;
  }

  public void setAgeTo(Integer ageTo) {
    this.ageTo = ageTo;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Event event = (Event) o;
    return Objects.equals(this.name, event.name) &&
        Objects.equals(this.location, event.location) &&
        Objects.equals(this.startDatetime, event.startDatetime) &&
        Objects.equals(this.endDatetime, event.endDatetime) &&
        Objects.equals(this.description, event.description) &&
        Objects.equals(this.images, event.images) &&
        Objects.equals(this.ageFrom, event.ageFrom) &&
        Objects.equals(this.ageTo, event.ageTo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, location, startDatetime, endDatetime, description, images, ageFrom, ageTo);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Event {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    startDatetime: ").append(toIndentedString(startDatetime)).append("\n");
    sb.append("    endDatetime: ").append(toIndentedString(endDatetime)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    images: ").append(toIndentedString(images)).append("\n");
    sb.append("    ageFrom: ").append(toIndentedString(ageFrom)).append("\n");
    sb.append("    ageTo: ").append(toIndentedString(ageTo)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}


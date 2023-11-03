<%-- 
    Document   : reviewforcus
    Created on : Nov 2, 2023, 5:01:36 PM
    Author     : nguye
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Product Rating and Reviews</title>
        <!-- Include your CSS styling here -->
        <link rel="stylesheet" type="text/css" href="styles.css">
    </head>
    <body>
        <h1>Product Rating and Reviews</h1>

        <h2>Product Name: Example Product</h2>

        <!-- Display the average rating -->
        <div class="average-rating">
            <p>Average Rating: 4.5 out of 5</p>
        </div>

        <!-- Form to submit a rating and review -->
        <form action="ReviewServlet" method="post">
            <label for="rating">Rating:</label>
            <select name="rating">
                <option value="5">5 stars</option>
                <option value="4">4 stars</option>
                <option value="3">3 stars</option>
                <option value="2">2 stars</option>
                <option value="1">1 star</option>
            </select>
            <br>
            <label for="review">Review:</label>
            <textarea name="review" rows="4" cols="50"></textarea>
            <br>
            <input type="submit" value="Submit Review">
        </form>

        <!-- Display existing reviews (you can loop through a list of reviews) -->
        <h2>Customer Reviews</h2>
        <div class="review">
            <div class="rating">Rating: 5 stars</div>
            <div class="comment">Great product! I highly recommend it.</div>
        </div>
        <div class="review">
            <div class="rating">Rating: 4 stars</div>
            <div class="comment">Good value for the price.</div>
        </div>

        <!-- Include your JavaScript if needed -->

    </body>
</html>


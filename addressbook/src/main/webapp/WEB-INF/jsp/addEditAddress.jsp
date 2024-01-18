<%@ include file="include.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="head.jsp"/>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-12">
            <h1>${heading}</h1>
            <jsp:include page="message.jsp"/>
            <form method="post" action="<%=request.getContextPath()%>/addEditAddress">
                <input type="hidden" id="addressId" name="addressId" value="${address.addressId}" />
                <div class="row form-group">
                    <div class="col-12 col-sm-4">
                        <label for="firstName"><b>First Name</b></label>
                        <input class="form-control" name="firstName" id="firstName"
                               placeholder="First Name" value="${address.firstName}">
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-12 col-sm-4">
                        <label for="lastName"><b>Last Name</b></label>
                        <input class="form-control" name="lastName" id="lastName"
                               placeholder="Last Name" value="${address.lastName}">
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-12 col-sm-4">
                        <label for="addressOne"><b>Address</b></label>
                        <input class="form-control" name="addressOne" id="addressOne"
                               placeholder="Address" value="${address.addressOne}">
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-12 col-sm-4">
                        <label for="addressTwo"><b>Address Two</b></label>
                        <input class="form-control" name="addressTwo" id="addressTwo"
                               placeholder="Address Two" value="${address.addressTwo}">
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-12 col-sm-4">
                        <label for="city"><b>City</b></label>
                        <input class="form-control" name="city" id="city"
                               placeholder="City" value="${address.city}">
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-12 col-sm-4">
                        <label for="state"><b>State</b></label>
                        <input class="form-control" name="state" id="state"
                               placeholder="State" value="${address.state}">
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-12 col-sm-4">
                        <label for="zip"><b>Address Two</b></label>
                        <input class="form-control" name="zip" id="zip"
                               placeholder="Zip" value="${address.zip}">
                    </div>
                </div>
                <button id="btnSubmit" type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello</title>
</head>
<body>
Hello,${name}

</br>

<br>
<a href="/testParameter?username=aaaaa">testParameter</a>
</br>

<br>
<a href="/param/getParametersFromProopFile">Test value from application.properties</a>
</br>

<br>
<a href="/param/getParametersUsingConfigurationProperties">Test value from application.properties by using ConfigurationProperties</a>
<br>

<p>SpringMVC</p>

<br>
<a href="/springmvc/doActioin?username='aaaaa'&password=11111">doActioin</a>
<br>

<br>
<a href="/springmvc/doActioinBean?name='aaaaa'&age=100">doActioinBean</a>
<br>

<br>
<a href="/springmvc/doActioin1?username='aaaaa'&password=11111">doActioin1, this would not working due to Request Method set to "Post" </a>
<br>

<br>
<form action="/springmvc/doActioin2" method="post">
    name : <input type="text" name="name"><br>
    age  : <input type="text" name="age"><br>
    price  : <input type="text" name="price"><br>
    street number  : <input type="text" name="address.streetNumber"><br>
    street name  : <input type="text" name="address.streetName"><br>
    effective date  : <input type="text" name="address.effectiveDate"><br>
    <input type="submit" value="doAction2 - Put data into Java Bean">
</form>
<br>



</body>
</html>






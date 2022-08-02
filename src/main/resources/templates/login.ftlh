<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<div>
    <h2>Пожалуйста войдите!</h2>
    <form action="/login" method="post">

        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>


        <div class="form-group">
            <label for="email">Адрес почты</label>
            <input type="email" name="username" class="form-control" id="email" placeholder="Ваш адрес почты"
                   value="" autofocus>
        </div>
        <div>
            <div class="form-group">
                <label for="password">Пароль:</label>
                <input type="password" class="form-control" name="password" id="password" placeholder="Пароль"
                       value="">
            </div>
        </div>
        <div>
            <button type="submit" class="btn btn-primary" style="margin-right: 15px">Войти</button>
        </div>
    </form>
    <div class="container mt-5">
        <a class="flex box-128" href="/register"> Регистрация </a>
    </div>
</div>
</body>
</html>
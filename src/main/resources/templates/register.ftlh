<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
    <h2>Please register</h2>
    <div>
    <form action="/register" method="post">

        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>

        <div>
            <label class="flex flex-v-center">
                <p class="label-input-text">Имя:</p>
                <input type="text" name="name" placeholder="ваше имя"  value="">
            </label>
        </div>
        <div>
            <label class="flex flex-v-center">
                <p class="label-input-text">Адрес почты:</p>
                <input type="text" name="email" placeholder="ваш адрес почты"  value="" >
            </label>
        </div>
        <div>
            <label class="flex flex-v-center">
                <p class="label-input-text">Пароль:</p>
                <input type="text" name="password" placeholder="пароль"  value="">
            </label>
        </div>
        <div>
            <button type="submit">Регистрация</button>
        </div>
    </form>
    </div>
    <div class="bottom-box">
        <a href="/">На главную страницу</a>
    </div>
</body>
</html>
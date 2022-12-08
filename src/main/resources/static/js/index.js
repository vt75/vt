document.getElementById("myHeader").innerHTML =
    "<div class='kontainer'>" +
    "<div class='row id = hd'>"+
    "<div class='col-md-6 id = text_M' > <a href='/product' style='color: #fff; text-decoration: none; ' >МАГАЗИН НОВОГО ГОДА </a></div>"+
    "<div class='col-md-2 id = text_M1'><a class = 'menu-item' style= 'text-decoration: none;' href='/authentication/login'>корзина</a></div>"+
    "<div class='col-md-2 id = text_M2'><a class = 'menu-item' style= 'text-decoration: none;' href='/authentication/login'>вход / регистрация</a></div>"+
    "</div></div>"+
    "<div class='kontainer'>"+
    "<div class='row id = bl1'>"+
    "<div class='col-md-1'><img class = 'bloc1' >"+
       "<input type='image' style='width: 100px; margin-top: -10px' src='/css/санта.png'  >"+
    "</div>"+
    "<div class='col-md-1'><img class = 'bloc1' >"+
    "<input type='image' style='width: 150px; height: 90px;' src='/css/надпись1.png'  >"+
    "</div>"+

    "<div class='col-md-6'><img class = 'bloc1' >"+
    "<p class='text-center'> <br> Интернет магазин новогодних игрушек. <br>"+
    " У нас самые лучшие игрушки для веселого праздника.</p>"+
    "</div>"+
    "<div class='col-md-3'><img class = 'bloc1' >"+
    "<br>"+
    "<form h:method= 'POST' th:action='@{/product/search}' class='navbar-form' role='search'>"+
    " <div class='input-group' style='width: 380px; margin-top: 10px'>"+
    "<input type='text' autocomplete='off' class='form-control' placeholder='Поиск' name='search' id='search'>"+
    "<span class='input-group-btn'>"+
    "<button class='btn' id='srchbtn' type='submit'>"+
    "<i class='fa fa-search' aria-hidden='true'></i>"+
    "</button>"+
    " </span>"+
    " </div>"+
    "</form>"+
    "</div></div></div>";

document.getElementById("myHead").innerHTML =
    "<meta charSet='utf-8'>"+
    " <meta name='viewport' content='width=device-width, initial-scale=1'>"+
    " <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css' rel='stylesheet'"+
    "integrity='sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx'"+
    "crossOrigin='anonymous'>"+
    " <link rel='stylesheet'"+
    " href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css'/>"+
    "<link rel='stylesheet'"+
    "href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'/>";

document.getElementById("myfooter").innerHTML =
    "<div className='kontainer'>"+
    "<div className='row id = ft'>"+
    "<div className='col-md-6' style='margin-left: 50px;'><br>Итоговая работа Сабашная Т.А.<br></div>"+
    "</div> </div>";


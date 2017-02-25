var app= angular.module("app",['ngRoute']);

  app.config(function ($routeProvider) {
    $routeProvider
      .when('/',{
        templateUrl :'./views/home.html',
        controller : 'HomeController'
      })
      .when('/users',{
        templateUrl:'./views/users.html',
        controller: 'UserController'
      })
  });

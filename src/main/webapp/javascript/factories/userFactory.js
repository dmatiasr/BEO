angular.module('app')
  .factory('UserFactory', [ '$http',function ($http) {
  var factory={};
  var users=[];

  factory.getUsers= function () {
      return $http.get("http://localhost:4567/users");
  }

  return factory;
}]);

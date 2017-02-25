  angular.module('app')
.controller('HomeController', function ($scope,$http,$location,$window) {
  console.log('3');
  $scope.users=[];
  var user={name :'Pedro', address :'alla'}


  load_users = function() {
    console.log('7');
    $http.get("http://localhost:4567/users").then(function callback(res) {

      if (res.status==200){
        $scope.users=res.data;
        console.log(res.data);
      }else{
        alert("load_users : ops error!");
      }
    });
  }

  load_users();
  console.log($scope);

});

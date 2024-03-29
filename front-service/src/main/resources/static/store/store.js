angular.module('market').controller('storeController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:5555/core/';
    const cartContextPath = 'http://localhost:5555/cart/';

    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            redirect: contextPath + 'shop',
            method: 'GET',
        })
    }

    $scope.showProductInfo = function (productId) {
        $http.get(contextPath + 'api/v1/products/' + productId).then(function (response) {
            alert(response.data.title);
        });
    }

    $scope.addToCart = function (productId) {
        $http.get(cartContextPath + 'api/v1/cart/' + $localStorage.winterMarketGuestCartId + '/add/' + productId).then(function (response) {
        });
    }

    $scope.loadProducts();
});
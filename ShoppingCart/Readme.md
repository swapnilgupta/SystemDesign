### Design Shopping Cart Using Decorator Pattern

1. **Product:** Abstract class representing a generic product with common attributes such as id, name, price, and type.
2.	**Item1** and **Item2**: Concrete classes representing specific types of products.
3.	_ProductType_: Enum defining different product categories like FURNITURE and ELECTRONICS.
4.	_ShoppingCart_: Manages the list of products and calculates the total price. It can also apply coupons through CouponDecorator.
5.	_CouponDecorator_: Abstract decorator class for coupons, ensuring that new coupon types can be easily added.
6.	_PercentageCouponDecorator_: Applies a percentage-based discount to the cart total.
7.	_TypeCouponDecorator_: Applies a discount based on the type of product.
8.	_Main_: `Demonstrates` the usage of the `ShoppingCart` and applying different types of coupons.
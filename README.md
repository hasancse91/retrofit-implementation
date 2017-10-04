# Network call (GET and POST method) with Retrofit Library

Have any suggestion? Any advice for good practice? Please send me a pull request.

### Quick Links of this Repository:
- [All Android Packages](https://github.com/hasancse91/retrofit-implementation/tree/master/Android%20Code/app/src/main/java/com/hellohasan/networkcallwithretrofit)
- [PHP server side code](https://github.com/hasancse91/retrofit-implementation/tree/master/PHP%20Code)

## Does this implementation make sense? Go for best practice...
I created [different-network-layer](https://github.com/hasancse91/retrofit-implementation/tree/different-network-layer) branch in this repository for a better implementation. In `master` branch I called `Retrofit` methods from my `Activity` class. But the network related implementation should in a different **network layer** not in **view layer**. You'll find an abstraction layer in this implementation. Click [**here**](https://github.com/hasancse91/retrofit-implementation/tree/different-network-layer) for full source code.

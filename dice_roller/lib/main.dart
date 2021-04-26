import 'dart:math';

import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: HomePage(),
    );
  }
}

class HomePage extends StatefulWidget {
  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  int _diceIndex = 1;

  void _roll() {
    setState(() {
      _diceIndex = Random().nextInt(6) + 1;
      print('Got $_diceIndex this time');
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.black,
      appBar: AppBar(
        backgroundColor: Colors.black,
        title: Text('Dice Roller App'),
      ),
      body: Center(
        child: GestureDetector(
          onTap: _roll,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Image.asset(
                'images/dice$_diceIndex.png',
                height: 100,
                width: 100,
              ),
              Padding(
                padding: const EdgeInsets.all(18.0),
                child: Text(
                  "$_diceIndex",
                  style: TextStyle(
                    fontSize: 25,
                    color: Colors.white
                  ),
                ),
              )
            ],
          ),
        ),
      ),
    );
  }
}

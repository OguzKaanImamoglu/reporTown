import { StatusBar } from 'expo-status-bar';
import React from 'react';
import { NavigationContainer } from "@react-navigation/native";
import { createStackNavigator } from "@react-navigation/stack";
import { createMaterialBottomTabNavigator } from "@react-navigation/material-bottom-tabs";
import { StyleSheet, Text, View } from 'react-native';
import LoginScreen from './app/screens/LoginScreen';
import WelcomeScreen from './app/screens/WelcomeScreen';
import RegisterChooseScreen from './app/screens/RegisterChooseScreen';
import CitizenRegisterScreen from './app/screens/CitizenRegisterScreen';
import InstitutionRegisterScreen from './app/screens/InstitutionRegisterScreen';
import SearchScreen from './app/screens/SearchScreen';
import MapScreen from './app/screens/MapScreen';
import PostScreen from './app/screens/PostScreen';
import ProfileScreen from './app/screens/ProfileScreen';
import FeedScreen from './app/screens/FeedScreen';
import { MaterialIcons } from "@expo/vector-icons";

const Stack = createStackNavigator();
const Tabs = createMaterialBottomTabNavigator();

const UserScreens = () => {
  return (
    <Tabs.Navigator
      initialRouteName="Feed"
      screenOptions={({ route }) => ({
        tabBarIcon: ({ color }) => {
          let iconName;

          if (route.name === "Feed") {
            iconName = "dynamic-feed";
          } else if (route.name === "Map") {
            iconName = "map";
          } else if (route.name === "Post") {
            iconName = "add";
          } else if (route.name === "Search") {
            iconName = "search";
          } else if (route.name === "Profile") {
            iconName = "person";
          }

          return <MaterialIcons name={iconName} size={24} color={color} />;
        },
      })}
      barStyle={{ backgroundColor: "#cb7b23" }}
    >
      <Tabs.Screen name="Search" component={SearchScreen} />
      <Tabs.Screen name="Map" component={MapScreen} />
      <Tabs.Screen name="Post" component={PostScreen} />
      <Tabs.Screen name="Feed" component={FeedScreen} />
      <Tabs.Screen name="Profile" component={ProfileScreen} />
    </Tabs.Navigator>
  );
};



export default function App() {

  return (
    <NavigationContainer>
      <Stack.Navigator headerMode="false" initialRouteName="LoginScreen">
        <Stack.Screen name="LoginScreen" component={LoginScreen} />
        <Stack.Screen name="SearchScreen" component={SearchScreen} />
        <Stack.Screen name="UserScreens" component={UserScreens} />
        <Stack.Screen name="MapScreen" component={MapScreen} />
        <Stack.Screen name="ProfileScreen" component={ProfileScreen} />
        <Stack.Screen name="PostScreen" component={PostScreen} />
        <Stack.Screen name="FeedScreen" component={FeedScreen} />
        <Stack.Screen name="WelcomeScreen" component={WelcomeScreen} />
        <Stack.Screen name="RegisterChooseScreen" component={RegisterChooseScreen} />
        <Stack.Screen name="CitizenRegisterScreen" component={CitizenRegisterScreen} />
        <Stack.Screen name="InstitutionRegisterScreen" component={InstitutionRegisterScreen} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}

import { StackActions, NavigationActions } from 'react-navigation';

let _navigator;

function setTopLevelNavigator(navigatorRef) {
  _navigator = navigatorRef;
}

function navigate(routeName, params) {
  _navigator.dispatch(
    NavigationActions.navigate({
      routeName,
      params,
    })
  );
}

function push() {
  _navigator.dispatch(StackActions.push())
}

function pop() {
  _navigator.dispatch(StackActions.pop())
}

function popToTop() {
  _navigator.dispatch(StackActions.popToTop())
}

export default {
  navigate,
  setTopLevelNavigator,
  popToTop,
  push,
  pop,
}
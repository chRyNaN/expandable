# expandable

An Android View Library with an layout that can easily be expanded or collapsed.

## Using the library

Wrap the view to be expandable in a `ExpandableMotionLayout` parent. The expandable view should be within a `ExpandableChildLayout` within the `ExpandableMotionLayout` parent.
The `ExpandableMotionLayout` extends from [MotionLayout](https://developer.android.com/reference/android/support/constraint/motion/MotionLayout) which extends from [ConstraintLayout](https://developer.android.com/reference/android/support/constraint/ConstraintLayout.html).

```xml
<com.chrynan.expandable.ExpandableMotionLayout
            android:id="@+id/expandableLayout"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:layoutDescription="@xml/video_scene"
            app:progress="@integer/expandable_layout_collapsed">

        <com.google.android.material.bottomnavigation.BottomNavigationView
                android:id="@+id/bottomNavigationView"
                android:layout_width="0dp"
                android:layout_height="@dimen/bottom_navigation_bar_height"
                app:menu="@menu/menu_bottom_navigation"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintBottom_toBottomOf="parent"/>

        <com.chrynan.expandable.ExpandableChildLayout
                android:id="@+id/videoFragmentContainer"
                android:layout_width="0dp"
                android:layout_height="wrap_content"
                app:transitionOnClick="false"
                app:layout_constraintBottom_toTopOf="@+id/bottomNavigationView"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintEnd_toEndOf="parent"/>

</com.chrynan.expandable.ExpandableMotionLayout>
```

Use a Motion Scene to direct the animation between the expanded and collapsed states. The expanded state is the end value.
```xml
<?xml version="1.0" encoding="utf-8"?>
<MotionScene xmlns:android="http://schemas.android.com/apk/res/android"
             xmlns:app="http://schemas.android.com/apk/res-auto">

    <Transition
            app:constraintSetStart="@+id/collapsed"
            app:constraintSetEnd="@+id/expanded"
            app:duration="1000">

        <OnSwipe
                app:touchAnchorSide="top"
                app:dragDirection="dragUp"
                app:touchAnchorId="@+id/videoFragmentContainer"/>

    </Transition>

    <ConstraintSet android:id="@+id/expanded">

        <Constraint
                android:id="@+id/videoFragmentContainer"
                android:layout_height="0dp"
                android:layout_width="0dp"
                android:layout_marginStart="0dp"
                android:layout_marginEnd="0dp"
                android:layout_marginBottom="0dp"
                android:layout_marginTop="0dp"
                app:progress="@integer/expandable_layout_expanded"
                app:layout_constraintTop_toTopOf="parent"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintEnd_toEndOf="parent"/>

        <Constraint
                android:id="@+id/bottomNavigationView"
                android:layout_width="0dp"
                android:layout_height="@dimen/bottom_navigation_bar_height"
                android:visibility="gone"
                android:translationY="@dimen/bottom_navigation_bar_height"
                app:progress="@integer/expandable_layout_expanded"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintBottom_toBottomOf="parent"/>

    </ConstraintSet>

    <ConstraintSet android:id="@+id/collapsed">

        <Constraint
                android:id="@+id/videoFragmentContainer"
                android:layout_width="0dp"
                android:layout_height="80dp"
                android:layout_marginStart="8dp"
                android:layout_marginEnd="8dp"
                android:layout_marginBottom="8dp"
                android:layout_marginTop="8dp"
                app:progress="@integer/expandable_layout_collapsed"
                app:layout_constraintBottom_toTopOf="@+id/bottomNavigationView"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintEnd_toEndOf="parent"/>

        <Constraint
                android:id="@+id/bottomNavigationView"
                android:layout_width="0dp"
                android:layout_height="@dimen/bottom_navigation_bar_height"
                android:visibility="visible"
                android:translationY="0dp"
                app:progress="@integer/expandable_layout_collapsed"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintBottom_toBottomOf="parent"/>

    </ConstraintSet>

</MotionScene>
```

Now the view should animate between it's collapsed and expanded states when the transition provided in the Motion Scene is triggered.

Alternatively, you can programmatically change the state:
```kotlin
expandableMotionLayout?.expand()
expandableMotionLayout?.collapse()
```

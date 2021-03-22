
import java.lang.*;
import java.util.*;
import java.util.stream.Collectors;

class CollectionsExplorer {

    // Returns element closest to target in arr[] 
//    public static double findClosest(double arr[], double target)
    public static double findClosest(double target, LinkedHashMap<Integer, CalculatedRowsAndIrisType> sortedMap)
    {
        Double[] arr = sortedMap.values().stream().map(CalculatedRowsAndIrisType::getSumOfT).toArray(Double[]::new);
        int n = arr.length;

        if (target <= arr[0])
            return arr[0];
        if (target >= arr[n - 1])
            return arr[n - 1];

        int i = 0, j = n, mid = 0;
        while (i < j) {
            mid = (i + j) / 2;

            if (arr[mid] == target)
                return arr[mid]; 

            if (target < arr[mid]) {

                if (mid > 0 && target > arr[mid - 1])
                    return getClosest(arr[mid - 1],
                            arr[mid], target);

                j = mid;
            }

            else {
                if (mid < n-1 && target < arr[mid + 1])
                    return getClosest(arr[mid],
                            arr[mid + 1], target);
                i = mid + 1;
            }
        }

        return arr[mid];
    }

    public static double getClosest(double val1, double val2,
                                 double target)
    {
        if (target - val1 >= val2 - target)
            return val2;
        else
            return val1;
    }

    public static List<String> findAllIrises(int k, LinkedHashMap<Integer, CalculatedRowsAndIrisType> sortedMap, double closest) {

        Double[] arr = sortedMap.values().stream().map(CalculatedRowsAndIrisType::getSumOfT).toArray(Double[]::new);

        int indexOfClosestElement = 0;
        for (int i=0; i<= arr.length;i++){
            if (arr[i]==closest){
                indexOfClosestElement = i;
                break;
            }
        }

        List<Double> theClosestElements = new ArrayList<>();

        for (int i=indexOfClosestElement; i<indexOfClosestElement+k;i++){
            theClosestElements.add(arr[i]);
        }

        for (int i=indexOfClosestElement; i>indexOfClosestElement-k;i--){
            if (theClosestElements.contains(arr[i])){
                continue;
            }
            theClosestElements.add(arr[i]);
        }
        Collections.sort(theClosestElements);

        List<Double> choosenList = new ArrayList<>();
        int halfOFChoosenList = theClosestElements.size() / 2;
        for (int i=halfOFChoosenList; i>0;i--){
            if (i==theClosestElements.size()/2){
                choosenList.add(closest);
                k--;
                continue;
            }
            if (k>=3){
                int distanceFromCenter = halfOFChoosenList-i;
                choosenList.add(theClosestElements.get(halfOFChoosenList+distanceFromCenter));
                choosenList.add(theClosestElements.get(halfOFChoosenList-distanceFromCenter));
                k-=2;
                continue;
            }
            if (k==1){
                int distanceFromCenter = halfOFChoosenList-i;
                Double upperValue = theClosestElements.get(halfOFChoosenList + distanceFromCenter);
                Double lowerValue = theClosestElements.get(halfOFChoosenList - distanceFromCenter);
                Double whichToInsert = Math.abs(closest-upperValue)>Math.abs(closest-lowerValue)? upperValue:lowerValue;
                choosenList.add(whichToInsert);
                k--;
                continue;
            }

        }


        List<CalculatedRowsAndIrisType> allCalculatedRowsAndIrisType = sortedMap.values().stream().collect(Collectors.toList());
        List<String> genresTypes = allCalculatedRowsAndIrisType.stream().filter(calculatedRowsAndIrisType -> choosenList.contains(calculatedRowsAndIrisType.getSumOfT()))
                .map(calculatedRowsAndIrisType -> calculatedRowsAndIrisType.getIrisName()).collect(Collectors.toList());

        return Collections.singletonList("");
    }
}
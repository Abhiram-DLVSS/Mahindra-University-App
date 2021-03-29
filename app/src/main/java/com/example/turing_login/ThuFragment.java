package com.example.turing_login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThuFragment extends Fragment {

    private static final String URL_DATA ="http://turing.infinityfreeapp.com/test.php";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private int count,total;
    private List<Listitem_thufrag> listitem_thufrags;
    //to fetch data
    DatabaseReference reff;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ThuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThuFragment newInstance(String param1, String param2) {
        ThuFragment fragment = new ThuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_thu, container, false);
        recyclerView= view.findViewById(R.id.recyclerView_thuFrag);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //loadRecyclerViewData();

        listitem_thufrags=new ArrayList<>();
//        for(int i=0;i<2;i++){
//            Listitem_thufrag listitem_thufrag=new Listitem_thufrag(
//                    "heading"+(i+1),"testing"
//            );
//            listitem_thufrags.add(listitem_thufrag);
//        }

        ReadHeader();
//        reff= FirebaseDatabase.getInstance().getReference().child("Thuday").child("0");
//        reff.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String m1=snapshot.child("header1").getValue().toString();
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        Listitem_thufrag listitem_thufrag=new Listitem_thufrag(
//                "heading"+(1),"testing"
//        );
//        listitem_thufrags.add(listitem_thufrag);
//
//
//        adapter=new ThuAdapter(listitem_thufrags,getContext());
//        recyclerView.setAdapter(adapter);
        return view;
    }
    private  void ReadHeader(){
        final FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference();
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();        //   DatabaseReference reference=FirebaseDatabase.getInstance().getReference("Thuday");
        reference.keepSynced(true);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listitem_thufrags.clear();
                String rollnumber=snapshot.child("Users").child(currentuser).child("id").getValue().toString();
                String year=rollnumber.substring(0,2);
                String branch=rollnumber.substring(7,8);
                String rno=rollnumber.substring(8,10);
                String batch;
                if(Integer.parseInt(rno)<42)
                    batch="1";
                else
                    batch="2";

                total=(int) snapshot.child("TimeTable").child(year).child(branch).child("1").child("Thursday").getChildrenCount();
                for(count=0;count<total;count++){
                    String chil=""+count;
                    String m1=snapshot.child("TimeTable").child(year).child(branch).child(batch).child("Thursday").child(chil).child("header").getValue().toString();
                    String m2=snapshot.child("TimeTable").child(year).child(branch).child(batch).child("Thursday").child(chil).child("time").getValue().toString();
                    String m3=snapshot.child("TimeTable").child(year).child(branch).child(batch).child("Thursday").child(chil).child("lecturer").getValue().toString();
                    Listitem_thufrag listitem_thufrag=new Listitem_thufrag(m1,m2,m3);
                    //  Listitem_thufrag listitem_thufrag=snapshot1.getValue(Listitem_thufrag.class);

                    assert listitem_thufrag != null;
                    listitem_thufrags.add(listitem_thufrag);

                    adapter=new ThuAdapter(listitem_thufrags,getContext());
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //  private void loadRecyclerViewData() {
//        ProgressDialog progressDialog = new ProgressDialog(getContext());
//        progressDialog.setMessage("Loading data....");
//        ProgressDialog.show();

//        StringRequest stringRequest=new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String s) {
//
//                try {
//                    JSONObject jsonObject=new JSONObject(s);
//                    JSONArray array=jsonObject.getJSONArray("Thuday");
//                    for(int i=0;i<array.length();i++){
//                        JSONObject o=array.getJSONObject(i);
//                        Listitem_thufrag item= new Listitem_thufrag(
//                        o.getString("header"+i),o.getString("header"+i)
//                        );
//
//                        listitem_thufrags.add(item);
//                    }
//                    adapter= new ThuAdapter(listitem_thufrags,getContext());
//                    recyclerView.setAdapter(adapter);
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        }
//        );
//        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
//        requestQueue.add(stringRequest);
//    }
}
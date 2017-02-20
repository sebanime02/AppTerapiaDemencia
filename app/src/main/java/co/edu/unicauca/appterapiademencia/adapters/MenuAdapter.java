package co.edu.unicauca.appterapiademencia.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;
import co.edu.unicauca.appterapiademencia.items.RowItem;
import co.edu.unicauca.appterapiademencia.R;

/**
 * Created by ENF on 27/10/2016.
 */

public class MenuAdapter extends ArrayAdapter<RowItem>{

    Context context;
    boolean selectedIndicator = false;
    List<RowItem> itemList;
    GreenDaoHelper daoHelper = GreenDaoHelper.getInstance();

    public MenuAdapter(Context context, int resourceId,
                       List<RowItem> items) {
        super(context, resourceId, items);
        this.context = context;
        this.itemList = items;
    }

    /*private view holder class*/
    private class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
        TextView txtDesc;
        TextView txtCount;

    }


    public int getItemCount() {

        if(itemList==null)
        {
            return 0;
        }
        else
        {
            return itemList.size();
        }


    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        RowItem rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_list, null);
            holder = new ViewHolder();

            holder.txtTitle = (TextView) convertView.findViewById(R.id.title);
            holder.imageView = (ImageView) convertView.findViewById(R.id.icon);
            holder.txtCount = (TextView) convertView.findViewById(R.id.pendingCountNote);



            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();


        holder.txtTitle.setText(rowItem.getTitle());
        holder.imageView.setImageResource(rowItem.getImageId());
        holder.txtCount.setVisibility(View.INVISIBLE);
        if(getItemCount()>4) //Supervisor o profesional en psicologia
        {
            if(rowItem.getImageId()==R.drawable.ic_action_content_report)
            {
                int count =0;
                try
                {
                    count = daoHelper.getNotePendingCount();
                    if(count>0)
                    {
                        Log.e("MenuAdapter"," count:"+count);
                        holder.txtCount.setText(" "+count+" ");

                        holder.txtCount.setVisibility(View.VISIBLE);

                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                }


            }

        }


        /*
        convertView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                    selectedIndicator = true;
            }
        });
        */


        /*
        if(selectedIndicator=true)
        {
            convertView.setBackgroundColor(context.getResources().getColor(R.color.gray));
        }
        */

        return convertView;
    }
}

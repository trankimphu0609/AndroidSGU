package com.trankimphu.lab04_uiclasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ToDoListAdapter extends BaseAdapter {

    private List<ToDoItem> items = new ArrayList<ToDoItem>();
    private Context context;

    class ToDoListViewHolder {

        TextView tvTitle, tvPriority, tvDate;
        CheckBox cbStatus;

        ToDoListViewHolder(LinearLayout layout) {
            tvTitle = (TextView) layout.findViewById(R.id.tvTitle2);
            tvPriority = (TextView) layout.findViewById(R.id.tvPriorityView);
            tvDate = (TextView) layout.findViewById(R.id.tvDateView);
            cbStatus = (CheckBox) layout.findViewById(R.id.cbStatus2);
        }
        void setItem(ToDoItem toDoItem) {
            tvTitle.setText(toDoItem.getTitle());

            cbStatus.setOnCheckedChangeListener(null);
            cbStatus.setChecked(toDoItem.getStatus() == ToDoItem.Status.DONE);

            tvPriority.setText(toDoItem.getPriority().toString());
            tvDate.setText(ToDoItem.FORMAT.format(toDoItem.getDate()));
        }
    }

    public ToDoListAdapter(Context context) {
        this.context = context;
    }

    // Add a ToDoItem to the adapter
    // Notify observers that the data set has changed
    public void add(ToDoItem item) {
        items.add(item);
        notifyDataSetChanged();
    }

    // Clears the list adapter of all items.
    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LinearLayout layout = (LinearLayout) view;

        // Use the view holder pattern to smooth out scrolling
        ToDoListViewHolder viewHolder;

        if (layout == null) {
            // Inflate the View for this ToDoItem
            // from activity_to_do_item.xml
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = (LinearLayout) inflater.inflate(R.layout.activity_to_do_item, viewGroup, false);

            viewHolder = new ToDoListViewHolder(layout);

            layout.setTag(viewHolder);

        }
        else {
            viewHolder = (ToDoListViewHolder) layout.getTag();
        }

        // Get the current ToDoItem
        ToDoItem toDoItem = (ToDoItem) getItem(i);
        viewHolder.setItem(toDoItem);

        viewHolder.cbStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    toDoItem.setStatus(ToDoItem.Status.DONE);
                else
                    toDoItem.setStatus(ToDoItem.Status.NOTDONE);
            }
        });

        return layout;
    }
}
